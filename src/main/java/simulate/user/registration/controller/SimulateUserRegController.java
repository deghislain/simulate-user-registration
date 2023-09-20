package simulate.user.registration.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simulate.user.registration.model.User;
import simulate.user.registration.service.SimulateUserRegService;
import simulate.user.registration.utils.InputValidator;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class SimulateUserRegController {
    @Autowired
    private InputValidator inputValidator;
    @Autowired
    private SimulateUserRegService service;

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user){

        if(inputValidator.isValidCredentials(user) && inputValidator.isValidIpAddress(user)){
           return this.service.registerUser(user);
        }else{
            String errorMessage = "";
            if(!inputValidator.isValidCredentials(user)){
                errorMessage = "Invalid Credentials";
            }else{
                errorMessage = "Invalid IP address";
            }
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

}
