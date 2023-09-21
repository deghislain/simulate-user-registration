package simulate.user.registration.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import simulate.user.registration.model.User;
import simulate.user.registration.service.SimulateUserRegService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SimulateUserRegController.class)
@AutoConfigureMockMvc
public class SimulateUserRegControllerTest extends  SimulateUserRegControllerBase{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SimulateUserRegService service;

    ResponseEntity<String> badReqResponse;
    @BeforeEach
    public void init() {
        badReqResponse = new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @Test
    void testValidUserInput() throws Exception {
        ResponseEntity<String> createdResponse = new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
        User user = new User();
        user.setUserId(1234456677);
        user.setUserName("username");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("127.0.0.1");
        when(this.service.registerUser(user)).thenReturn(createdResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                        .andExpect(status().isCreated());

    }
    @Test
    void testNonCanadianIpAddress() throws Exception {
        User user = new User();
        user.setUserId(111);
        user.setUserName("username");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("1.0.0.0");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());

    }
    @Test
    void testBlankUserName() throws Exception {
        User user = new User();
        user.setUserId(111);
        user.setUserName(" ");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("127.0.0.1");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyUserName() throws Exception {
        User user = new User();
        user.setUserId(111);
        user.setUserName("");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("127.0.0.1");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBlankPassword() throws Exception {
        User user = new User();
        user.setUserId(111);
        user.setUserName("username");
        user.setPassword(" ");
        user.setIpAddress("127.0.0.1");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyPassword() throws Exception {
        User user = new User();
        user.setUserId(111);
        user.setUserName("username");
        user.setPassword("");
        user.setIpAddress("127.0.0.1");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBlankIpAddress() throws Exception {
        User user = new User();
        user.setUserId(111);
        user.setUserName(" ");
        user.setPassword("Mygoodpass$1");
        user.setIpAddress("127.0.0.1");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyIpAddress() throws Exception {
        User user = new User();
        user.setUserId(111);
        user.setUserName("");
        user.setPassword("Mygoodpass$1");
        user.setIpAddress("127.0.0.1");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }
}
