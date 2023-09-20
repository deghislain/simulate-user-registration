package simulate.user.registration.utils;

import org.springframework.stereotype.Component;
import simulate.user.registration.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InputValidator {

    public boolean isValidCredentials(User user){
        return this.validateCredentials(user);
    }

    private boolean validateCredentials(User user){
        if(user != null) {
            if (user.getUserName() == null || user.getUserName().isEmpty() || user.getUserName().isBlank()
                    || user.getUserName().length()<2 || user.getUserName().length()>30) {
                return false;
            }
            if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                return false;
            }

            if(!isValidPassword(user.getPassword())){
                return false;
            }
        }
        return true;
    }

    public boolean isValidIpAddress(User user){
        return this.validateIpAddress(user.getIpAddress());
    }
private boolean validateIpAddress(String ip){
    if (ip == null || ip.isEmpty() || ip.isBlank()) {
        return false;
    }
    String regex = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(ip);
    return m.matches();
}
    private boolean isValidPassword(String pass){
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[#$%+=])"
                + "(?=\\S+$).{8,30}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pass);
        return m.matches();
    }

}
