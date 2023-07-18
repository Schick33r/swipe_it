package zandb.software.swipeit.data.user.controller;


import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import zandb.software.swipeit.data.user.dto.SwipeItUserDTO;
import zandb.software.swipeit.data.user.service.ClientService;
import zandb.software.swipeit.data.user.service.SupplierService;
import zandb.software.swipeit.data.user.service.SwipeItUserDetailsService;
import zandb.software.swipeit.security.JwtTokenUtil;

import java.util.Base64;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SwipeItUserDetailsService swipeItUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register/supplier")
    public ResponseEntity<String> registerSupplier(@RequestHeader("Authorization") String authorizationHeader) {
        return createAccount(authorizationHeader, true);

    }

    @PostMapping("/register/client")
    public ResponseEntity<String> registerClient(@RequestHeader("Authorization") String authorizationHeader) {
        return createAccount(authorizationHeader, false);
    }

    private ResponseEntity<String> createAccount(String authorizationHeader, boolean supplier) {
        if (!authorizationHeader.startsWith("Basic ")) {
            return ResponseEntity.badRequest().build();
        }

        String usernameAndPasswordString = new String(Base64.getDecoder().decode(authorizationHeader.substring(6)));

        String[] usernameAndPassword = usernameAndPasswordString.split(":");

        if (usernameAndPassword.length != 2) {
            return ResponseEntity.badRequest().build();
        }

        String username = usernameAndPassword[0];

        String password = passwordEncoder.encode(usernameAndPassword[1]);

        if (supplier) {
            supplierService.createSupplierWithUsernameAndPassword(username, password);
        } else {
            clientService.createClientWithUsernameAndPassword(username, password);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestHeader("Authorization") String authorizationHeader) {
        if (!authorizationHeader.startsWith("Basic ")) {
            return ResponseEntity.badRequest().build();
        }

        String usernameAndPasswordString = new String(Base64.getDecoder().decode(authorizationHeader.substring(6)));

        String[] usernameAndPassword = usernameAndPasswordString.split(":");

        if (usernameAndPassword.length != 2) {
            return ResponseEntity.badRequest().build();
        }

        String username = usernameAndPassword[0];

        String password = usernameAndPassword[1];

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetails swipeItUserDetails = swipeItUserDetailsService.loadUserByUsername(username);

        String jwtToken = jwtTokenUtil.generateToken(swipeItUserDetails.getUsername());

        JSONObject responseBody = new JSONObject();

        responseBody.put("jwtToken", jwtToken);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseBody.toString());
    }
}
