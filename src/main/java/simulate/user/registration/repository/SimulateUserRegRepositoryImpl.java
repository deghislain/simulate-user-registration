package simulate.user.registration.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import simulate.user.registration.model.User;
import simulate.user.registration.utils.InputValidator;

@Repository
@Transactional
@Slf4j
public class SimulateUserRegRepositoryImpl implements SimulateUserRegRepository{
    @Autowired
    EntityManager em;
    public User saveUser(User user){
        InputValidator inputValidator = new InputValidator();
        try {
            if (inputValidator.isValidCredentials(user) && inputValidator.isValidIpAddress(user)) {
                if (user.getUserId() == null) {
                    em.persist(user);
                } else {
                    em.merge(user);
                }
            } else {
                return null;
            }
        }catch (Exception e){
            log.error("Error while storing user {}", user.getUserName());
            return null;
        }
            return user;
    }

}
