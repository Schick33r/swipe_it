package zandb.software.swipeit.data.tokenblacklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import zandb.software.swipeit.data.tokenblacklist.service.BlacklistedJwtTokenService;

@RestController
public class LogoutController {

  @Autowired
  private BlacklistedJwtTokenService blacklistedJwtTokenService;

  @PostMapping("/logoutUser")
  public ResponseEntity<String> logoutCurrentUser(
      @RequestHeader("Authorization") String authorizationHeader) {
    if (!authorizationHeader.startsWith("Bearer ")) {
      return ResponseEntity.badRequest().body("The authorization header is malformed!");
    }

    String jwtToken = authorizationHeader.substring(7);

    blacklistedJwtTokenService.logoutUser(jwtToken);

    return ResponseEntity.ok("Successfully logged out!");
  }
}
