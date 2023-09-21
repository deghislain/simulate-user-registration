package simulate.user.registration.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import simulate.user.registration.model.User;

@Repository
@Transactional
public class SimulateUserRegRepository {
    @Autowired
    EntityManager em;

    public User saveUser(User user){
        if(user!= null) {
            if (user.getUserId() == null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
        }
            return user;
    }
}
