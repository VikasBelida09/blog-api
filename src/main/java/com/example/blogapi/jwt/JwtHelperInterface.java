package com.example.blogapi.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtHelperInterface {
    // isTokenValid
    boolean isTokenValid(String token, UserDetails userDetails);
    // isTokenExpired
    boolean isTokenExpired(String token);
    // extractUserName
    String extractUserName(String token);
    // extractExpirationTime
    Date extractExpirationTime(String token);
    // extractClaims
    Claims extractClaims(String token);
    // extractClaim
    <T> T extractClaim(String token, Function<Claims,T>resolver);
    // createToken
    String createToken(Map<String,Object>claims, String userName);
}
