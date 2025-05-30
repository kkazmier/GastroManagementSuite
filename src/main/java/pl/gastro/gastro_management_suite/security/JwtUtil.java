package pl.gastro.gastro_management_suite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    public String generateToken(UserDetails userDetails) {
        // przygotuj klucz:
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", "CHEF")

                /*
                .claim("role", userDetails.getAuthorities().stream()
                        .map(auth -> auth.getAuthority())
                        .findFirst()
                        .orElse(null))  // dodajemy rolę do claims
*/
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }


    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername()) &&
                !Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getExpiration()
                        .before(new Date());
    }
}
