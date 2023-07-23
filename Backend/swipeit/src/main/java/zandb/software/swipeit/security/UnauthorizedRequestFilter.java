package zandb.software.swipeit.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import zandb.software.swipeit.data.tokenblacklist.service.BlacklistedJwtTokenService;
import zandb.software.swipeit.data.user.service.SwipeItUserDetailsService;

@Component
public class UnauthorizedRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private SwipeItUserDetailsService swipeItUserDetailsService;

  @Autowired
  private BlacklistedJwtTokenService blacklistedJwtTokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      throw new IllegalArgumentException("The authorization header is missing or malformed");
    }

    String jwtToken = authorizationHeader.substring(7);

    if (blacklistedJwtTokenService.isTokenBlacklisted(jwtToken)) {
      throw new IllegalArgumentException(
          "The token was already blacklisted, because the user logged out!");
    }

    Claims jwtTokenClaims = jwtTokenUtil.getClaimsFromToken(jwtToken);

    if (jwtTokenClaims.getExpiration().before(new Date())) {
      throw new IllegalArgumentException("The JWT token is expired!");
    }

    String username = jwtTokenClaims.getSubject();

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails swipeItUserDetails = swipeItUserDetailsService.loadUserByUsername(username);

      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          swipeItUserDetails, null, swipeItUserDetails.getAuthorities());
      usernamePasswordAuthenticationToken
          .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getServletPath();
    return path.startsWith("/register") || path.startsWith("/authenticate");
  }
}
