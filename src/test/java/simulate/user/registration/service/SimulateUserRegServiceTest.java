package simulate.user.registration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import simulate.user.registration.model.User;
import simulate.user.registration.repository.SimulateUserRegRepository;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class SimulateUserRegServiceTest extends SimulateUserRegServiceTestBase{
    @MockBean
    private RestTemplate rest;
    @MockBean
    private RestTemplateBuilder builder;
    @Value( "${geolocation.url}")
    private String geolocationUrl;
    @Autowired
    private SimulateUserRegService service;
    @MockBean
    private SimulateUserRegRepository repository;

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
    public void testValidUserInput(){
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " From " + "Bedford"
                + "\n" + "Registration Successfully Completed";
            when(builder.build()).thenReturn(rest);
            geolocationUrl += "24.215.85.18";
            ResponseEntity<String> response = getGeoLocationMockCaResponse();
            when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);
        when(this.repository.saveUser(user)).thenReturn(user);
        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
    }
    @Test
    public void testNonCanadianIPInput(){
        user.setIpAddress("1.0.0.0");
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Only Canadian IP are allowed";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += "1.0.0.0";
        ResponseEntity<String> response = getGeoLocationMockNonCaResponse();
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    public void testBlankUserName(){
        user.setUserName(" ");
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Invalid Credentials";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += "24.215.85.18";
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(welcomeMessage);
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void testEmptyUserName() throws Exception {
        user.setUserName("");
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Invalid Credentials";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += "1.0.0.0";
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(welcomeMessage);
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void testNullUserName() throws Exception {
        user.setUserName(null);
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Invalid Credentials";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += "1.0.0.0";
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(welcomeMessage);
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }
    @Test
    void testBlankPassword() throws Exception {
        user.setPassword(" ");
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Invalid Credentials";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += "24.215.85.18";
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(welcomeMessage);
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }
    @Test
    void testEmptyPassword() throws Exception {
        user.setPassword("");
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Invalid Credentials";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += "24.215.85.18";
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(welcomeMessage);
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }
    @Test
    void testNullPassword() throws Exception {
        user.setPassword(null);
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Invalid Credentials";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += "24.215.85.18";
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(welcomeMessage);
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }
    @Test
    void testBlankIpAddress() throws Exception {
        user.setIpAddress(" ");
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Invalid IP address";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += " ";
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(welcomeMessage);
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void testEmptyIpAddress() throws Exception {
        user.setIpAddress("");
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Invalid IP address";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += "";
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(welcomeMessage);
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void testNullIpAddress() throws Exception {
        user.setIpAddress(null);
        String welcomeMessage = new Random().nextInt() + " " + user.getUserName() + " Invalid IP address";
        when(builder.build()).thenReturn(rest);
        geolocationUrl += "";
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(welcomeMessage);
        when(rest.getForEntity(geolocationUrl, String.class)).thenReturn(response);

        ResponseEntity<String> resp = this.service.registerUser(user);
        assertNotNull(resp);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

}
