package zandb.software.swipeit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

  public static final long JWT_TOKEN_VALIDITY = 60L * 60 * 24 * 365 * 1000;

  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(String username) {
    return Jwts.builder()
        .setClaims(new HashMap<>())
        .setSubject(username)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512)
        .compact();
  }

  public Claims getClaimsFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build()
        .parseClaimsJws(token).getBody();
  }

  public Date getExpirationDateFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build()
        .parseClaimsJws(token).getBody().getExpiration();
  }
}
