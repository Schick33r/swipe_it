package zandb.software.swipeit.data.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zandb.software.swipeit.data.user.dto.ClientDTO;
import zandb.software.swipeit.data.user.dto.SupplierDTO;
import zandb.software.swipeit.data.user.dto.SwipeItUserDTO;
import zandb.software.swipeit.data.user.exceptions.UserNotFoundException;
import zandb.software.swipeit.data.user.service.ClientService;
import zandb.software.swipeit.data.user.service.SupplierService;
import zandb.software.swipeit.data.user.service.SwipeItUserService;
import zandb.software.swipeit.security.AccessDeniedException;

@RestController
public class UserController {

    @Autowired
    private SwipeItUserService swipeItUserService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/user")
    public ResponseEntity<?> getClient() {
        try {
            return ResponseEntity.ok(swipeItUserService.getClient());
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


    @PutMapping("/client")
    public ResponseEntity<?> updateClientById(@RequestBody ClientDTO clientDTO) {
        try {
            return ResponseEntity.ok(clientService.updateClient(clientDTO));
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/supplier")
    public ResponseEntity<?> updateSupplierById(@RequestBody SupplierDTO supplierDTO) {
        try {
            return ResponseEntity.ok(supplierService.updateClient(supplierDTO));
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
