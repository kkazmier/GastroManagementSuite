package pl.gastro.gastro_management_suite.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long validityMs;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String base64Secret,
            @Value("${jwt.expiration-ms}") long validityMs) {
        this.secretKey = Jwts.SIG.HS256.key().build();
        this.validityMs = validityMs;
    }

    public String generateToken(String username) {
        Date now    = new Date();
        Date expiry = new Date(now.getTime() + validityMs);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        // Używamy verifyWith(...) zamiast deprecated setSigningKey(...)
        Jws<Claims> jws = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
        return jws.getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            // tu możesz zalogować szczegóły błędu, np. log.error("Invalid JWT", ex);
            return false;
        }
    }
}
