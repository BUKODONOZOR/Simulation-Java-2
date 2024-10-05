package com.Main.Simulation.util;

import com.Main.Simulation.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtil {
    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    public SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String getToken(Map<String, Object> claims, User user) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 10 )) // 10 seconds
                .signWith(this.getKey())
                .compact();
    }

    public String getToken(User user){
        //Crear el map de claims
        Map<String,Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("role", user.getRole().name());

        return getToken(claims, user);
    }

    public Claims getAllClaims(String token){
        return Jwts
                .parser() //Desarmar el jwt
                .verifyWith(this.getKey()) //Validamos que sea la misma firma con la se creo
                .build() //Lo construimos de nuevo
                .parseSignedClaims(token) // convertimos el token a base 10 - base64
                .getPayload(); //Extraemos el payload (claims)
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){

        //Obtener los claims
        final Claims claims = this.getAllClaims(token);

        return claimsResolver.apply(claims);

    }

    //Método para obtener el subject del jwt
    public String getUsernameFromToken(String token){
        return this.getClaim(token, Claims::getSubject );
    }

    //Obtener la fecha de expiración del token
    public Date getExpiration(String token){
        return this.getClaim(token, Claims::getExpiration);
    }

    //Método para validar si el token está expirado
    public boolean isTokenExpired(String token){
        return this.getExpiration(token).before(new Date());
    }

    //Método para validar si el token es válido
    public boolean isTokeValid(String token, UserDetails user){

        String userName= this.getUsernameFromToken(token);
        //Si el usuario que viene en el token coincide con alguno de la db y ademas el token no está expirado entonces es  válido
        return userName.equals(user.getUsername()) && !this.isTokenExpired(token);
    }

}
