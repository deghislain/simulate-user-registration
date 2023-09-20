package simulate.user.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simulate.user.registration.service.SimulateUserRegService;
import simulate.user.registration.service.SimulateUserRegServiceImpl;
import simulate.user.registration.utils.InputValidator;

@RestController
@RequestMapping(path = "/user/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public class SimulateUserRegController {
    @Autowired
    private InputValidator inputValidator;
    @Autowired
    private SimulateUserRegService service;

    @GetMapping("/{username}/{password}/{ipAddress}")
    public ResponseEntity<String> registerUser(@PathVariable String username, @PathVariable String password, @PathVariable String ipAddress){
        if(inputValidator.isValidRegisterUserInput(username, password, ipAddress)){
            service.registerUser(username, password, ipAddress);
           return ResponseEntity.status(HttpStatus.OK).body("OK");
        }else{
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Input");
        }
    }

}
