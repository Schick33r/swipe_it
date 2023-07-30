package zandb.software.swipeit.data.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zandb.software.swipeit.data.user.dto.SwipeItUserDTO;
import zandb.software.swipeit.data.user.exceptions.UserNotFoundException;
import zandb.software.swipeit.data.user.service.SwipeItUserService;
import zandb.software.swipeit.security.AccessDeniedException;

@RestController
public class UserController {

  @Autowired
  private SwipeItUserService swipeItUserService;

  @GetMapping("/user")
  public ResponseEntity<?> getClient() {
    try {
      return ResponseEntity.ok(swipeItUserService.getUser());
    } catch (UserNotFoundException e) {
      return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    } catch (AccessDeniedException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }


  @PutMapping("/user")
  public ResponseEntity<?> updateUserById(@RequestBody SwipeItUserDTO swipeItUserDTO) {
    try {
      return ResponseEntity.ok(swipeItUserService.updateUser(swipeItUserDTO));
    } catch (UserNotFoundException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
