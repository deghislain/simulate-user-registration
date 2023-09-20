package simulate.user.registration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public class SimulateUserRegController {
    @GetMapping("/{username}/{password}/{ipAddress}")
    public ResponseEntity<String> registerUser(@PathVariable String username, @PathVariable String password, @PathVariable String ipAddress){
        if(isValidInput(username, password, ipAddress)){
           return ResponseEntity.status(HttpStatus.OK).body("OK");
        }else{
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Input");
        }
    }

    private boolean isValidInput(String username, String password, String ipAddress){
        if(username == null || username.isEmpty() || username.isBlank() || username.equals("")){
            return false;
        }
        if(password == null || password.isEmpty() || password.isBlank()){
            return false;
        }
        if(ipAddress == null || ipAddress.isEmpty() || ipAddress.isBlank()){
            return false;
        }
        return true;
    }
}
