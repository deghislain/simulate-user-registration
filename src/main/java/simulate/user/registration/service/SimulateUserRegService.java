package simulate.user.registration.service;

import org.springframework.http.ResponseEntity;
import simulate.user.registration.model.User;

public interface SimulateUserRegService {
    public ResponseEntity<String> registerUser(User user);
}
