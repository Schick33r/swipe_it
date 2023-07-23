package zandb.software.swipeit.data.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zandb.software.swipeit.data.property.dto.PropertyDTO;
import zandb.software.swipeit.data.property.exceptions.PropertyDoesNotBelongToLoggedInUser;
import zandb.software.swipeit.data.property.exceptions.PropertyNotFoundException;
import zandb.software.swipeit.data.property.service.PropertyService;
import zandb.software.swipeit.security.UserIsNotSupplierException;

@RestController
public class PropertyController {

  @Autowired
  private PropertyService propertyService;

  @PostMapping("/property")
  public ResponseEntity<?> addProperty(@RequestBody PropertyDTO propertyDTO) {
    try {
      return ResponseEntity.ok(propertyService.createProperty(propertyDTO));
    } catch (UserIsNotSupplierException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/property/{id}")
  public ResponseEntity<?> updateProperty(@RequestBody PropertyDTO propertyDTO,
      @PathVariable long id) {
    try {
      return ResponseEntity.ok(propertyService.updateProperty(id, propertyDTO));
    } catch (UserIsNotSupplierException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (PropertyNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (PropertyDoesNotBelongToLoggedInUser e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }

  @PutMapping("/property/{id}/delete")
  public ResponseEntity<?> deleteProperty(@PathVariable long id) {
    try {
      propertyService.deleteProperty(id);
      return ResponseEntity.ok().build();
    } catch (UserIsNotSupplierException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (PropertyNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (PropertyDoesNotBelongToLoggedInUser e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }
}
