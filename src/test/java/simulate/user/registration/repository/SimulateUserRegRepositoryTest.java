package simulate.user.registration.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import simulate.user.registration.model.User;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
public class SimulateUserRegRepositoryTest {
    @Autowired
    private SimulateUserRegRepository repository;
    @MockBean
    EntityManager em;
    private  User user;

    @BeforeEach
    public void init() {
        user = new User();
        user.setUserId(new Random().nextInt());
        user.setUserName("username");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("24.215.85.18");
    }

    @Test
    void testStoreValidUserInput() throws Exception {
        user.setUserId(null);
        User savedUser =  repository.saveUser(user);

        verify(em, times(1)).persist(user);
        assertNotNull(savedUser);
        assertEquals("username", savedUser.getUserName());
    }

    @Test
    public void testBlankUserName(){
        user.setUserName(" ");
        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testEmptyUserName(){
        user.setUserName("");
        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testNullUserName(){
        user.setUserName(null);
        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testBlankPassword(){
        user.setPassword(" ");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testEmptyPassword(){
        user.setPassword("");
        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testNullPassword(){
        user.setPassword(null);

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testBlankIpAddress(){
        user.setIpAddress(" ");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testEmptyIpAddress(){
        user.setIpAddress("");

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }

    @Test
    public void testNullIpAddress(){
        user.setIpAddress(null);

        User savedUser =  repository.saveUser(user);

        verify(em, times(0)).persist(user);
        assertNull(savedUser);
    }
}
