package simulate.user.registration.utils;

import org.springframework.stereotype.Component;
import simulate.user.registration.model.User;

@Component
public class InputValidator {

    public boolean isValidRegisterUserInput(User user){
        return this.validateRegisterUserInput(user);
    }

    private boolean validateRegisterUserInput(User user){
        if(user != null) {
            if (user.getUserName() == null || user.getUserName().isEmpty() || user.getUserName().isBlank()) {
                return false;
            }
            if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                return false;
            }
            if (user.getIpAddress() == null || user.getIpAddress().isEmpty() || user.getIpAddress().isBlank()) {
                return false;
            }
        }
        return true;
    }

}
