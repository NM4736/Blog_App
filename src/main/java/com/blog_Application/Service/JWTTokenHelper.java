package com.blog_Application.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JWTTokenHelper {

    private final String SECRET_KEY = "password"; // Replace with your own secret key
    private final long EXPIRATION_TIME_MS = 3600000; // 1 hour token expiry time
    private final SecretKey SECRET_KEY_INSTANCE = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
// You can add more custom claims as needed, e.g., roles, permissions, etc.


        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION_TIME_MS);

        return Jwts.builder()
                .setClaims(claims)  // claim is nothing but payload
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY_INSTANCE)
                .compact();
    }

    public Map<String, Object> parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(SECRET_KEY_INSTANCE)
                .parseClaimsJws(token);

        return claimsJws.getBody();
    }

    public String getUserNameFromToken(String token) {
        Map<String, Object> claims = parseToken(token);
        return (String) claims.get("username");
    }

    public Date getExpirationDateFromToken(String token) {
        Claims claims = (Claims) parseToken(token);
        return claims.getExpiration();
    }

    public Object getClaimFromToken(String token, String claimName) {
        Map<String, Object> claims = parseToken(token);
        return claims.get(claimName);
    }

    public Map<String, Object> getAllClaimFromToken(String token) {
        return parseToken(token);
    }

    public  boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public  boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY_INSTANCE)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        // Step 1: Validate the token signature and extract claims
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY_INSTANCE)
                    .parseClaimsJws(token)
                    .getBody();

            // Step 2: Check if the username in the token matches the UserDetails username
            String usernameFromToken = (String) claims.get("username");;
            String usernameFromUserDetails = userDetails.getUsername();

            return usernameFromToken.equals(usernameFromUserDetails);

        } catch (Exception ex) {
            // Token validation failed, return false
            return false;
        }
    }
}
