package simulate.user.registration.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import simulate.user.registration.model.User;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
public class SimulateUserRegRepositoryTest {
    @Autowired
    private SimulateUserRegRepository repository;
    @MockBean
    EntityManager em;

    @Test
    void testStoreValidUserInput() throws Exception {
        User user = new User();
        user.setUserName("username");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("24.215.85.18");
        User savedUser =  repository.saveUser(user);

        verify(em, times(1)).persist(user);
        assertNotNull(savedUser);
        assertEquals("username", savedUser.getUserName());
    }

    @Test
    public void testBlankUserName(){
        User user = new User();
        user.setUserId(1234456677);
        user.setUserName(" ");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("24.215.85.18");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testEmptyUserName(){
        User user = new User();
        user.setUserId(1234456677);
        user.setUserName("");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("24.215.85.18");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testNullUserName(){
        User user = new User();
        user.setUserId(1234456677);
        user.setUserName(null);
        user.setPassword("Mypass1jg$");
        user.setIpAddress("24.215.85.18");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testBlankPassword(){
        User user = new User();
        user.setUserId(1234456677);
        user.setUserName("username");
        user.setPassword(" ");
        user.setIpAddress("24.215.85.18");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testEmptyPassword(){
        User user = new User();
        user.setUserId(1234456677);
        user.setUserName("username");
        user.setPassword("");
        user.setIpAddress("24.215.85.18");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testNullPassword(){
        User user = new User();
        user.setUserId(1234456677);
        user.setUserName("username");
        user.setPassword(null);
        user.setIpAddress("24.215.85.18");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testBlankIpAddress(){
        User user = new User();
        user.setUserName("username");
        user.setPassword("Mypass1jg$");
        user.setIpAddress(" ");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testEmptyIpAddress(){
        User user = new User();
        user.setUserId(1234456677);
        user.setUserName("username");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testNullIpAddress(){
        User user = new User();
        user.setUserId(1234456677);
        user.setUserName("username");
        user.setPassword("Mypass1jg$");
        user.setIpAddress(null);

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }
}
