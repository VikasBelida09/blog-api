package com.example.blogapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper implements JwtHelperInterface {
     private static final String SECRET = "77397A24432646294A404E635266556A586E327235753878214125442A472D4B!";

    @Value("${jwt.expiration-time}")
    private static Long ExpirationTime;

 private Key getSignKey() {
     byte[] key= Decoders.BASE64.decode(SECRET);
     return Keys.hmacShaKeyFor(key);
 }
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractExpirationTime(token).before(new Date());
    }

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpirationTime(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public Claims extractClaims(String token) {
        return Jwts.
                parser()
                .setSigningKey(this.getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims=extractClaims(token);
        return resolver.apply(claims);
    }

    @Override
    public String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();    }
}
