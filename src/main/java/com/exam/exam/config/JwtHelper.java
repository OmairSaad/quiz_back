package com.exam.exam.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {
    private SecretKey secretKey;

    public JwtHelper() throws NoSuchAlgorithmException {
        KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
        secretKey = key.generateKey();
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        // claims.put("username", username); // Example claim
        // claims.put("role", "user"); // Example role claim

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (60 * 60 * 60 * 30))) // 10 hours expiration
                .and()
                .signWith(secretKey)
                .compact();

    }

    public String extractUserName(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return (Claims) Jwts.parser().verifyWith(secretKey).build().parse(token).getPayload();
    }

    private Date getExpDate(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    private boolean isExpireToken(String token) {
        Date expire = getExpDate(token);
        return expire.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isExpireToken(token));
    }
}
