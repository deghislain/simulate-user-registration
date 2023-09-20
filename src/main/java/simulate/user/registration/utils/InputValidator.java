package simulate.user.registration.utils;

import org.springframework.stereotype.Component;

@Component
public class InputValidator {

    public boolean isValidRegisterUserInput(String username, String password, String ipAddress){
        return this.validateRegisterUserInput(username,password,ipAddress);
    }

    private boolean validateRegisterUserInput(String username, String password, String ipAddress){
        if(username == null || username.isEmpty() || username.isBlank()){
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
