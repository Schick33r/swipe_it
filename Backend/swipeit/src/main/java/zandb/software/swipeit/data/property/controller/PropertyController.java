package zandb.software.swipeit.data.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zandb.software.swipeit.data.property.dto.PropertyDTO;
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
}
