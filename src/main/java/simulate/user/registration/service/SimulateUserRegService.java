package simulate.user.registration.service;

import org.springframework.http.ResponseEntity;

public interface SimulateUserRegService {
    public ResponseEntity<String> registerUser(String username, String password, String ipAddress);
}
