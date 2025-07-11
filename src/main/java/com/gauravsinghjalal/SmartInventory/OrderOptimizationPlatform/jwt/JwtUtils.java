package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private final String SECRET="";
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*5))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }
    public String extractusername(String token){
        return  Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(token).getBody().getSubject();
    }
    public  boolean validateToken(String token, UserDetails userDetails){
        String username= extractusername(token);
        return  username.equals(userDetails.getUsername());
    }
}
