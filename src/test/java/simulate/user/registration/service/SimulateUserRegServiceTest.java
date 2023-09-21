package simulate.user.registration.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class SimulateUserRegServiceTest {
    @Autowired
    private SimulateUserRegService service;

    @Test
    public void testValidUserInput(){

    }

}
