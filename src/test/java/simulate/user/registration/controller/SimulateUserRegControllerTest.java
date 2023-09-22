package simulate.user.registration.controller;


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

import java.util.Random;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SimulateUserRegController.class)
@AutoConfigureMockMvc
public class SimulateUserRegControllerTest extends SimulateUserRegControllerTestBase {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SimulateUserRegService service;

    ResponseEntity<String> badReqResponse;

   private  User user;
    @BeforeEach
    public void init() {
        badReqResponse = new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        user = new User();
        user.setUserId(new Random().nextInt());
        user.setUserName("username");
        user.setPassword("Mypass1jg$");
        user.setIpAddress("24.215.85.18");
    }
    @Test
    void testValidUserInput() throws Exception {
        String messageToUser = user.getUserId() + " Welcome " +user.getUserName() +" From Bedford"
                + "\n" + "Registration Successfully Completed";
        ResponseEntity<String> createdResponse = ResponseEntity.status(HttpStatus.CREATED).body(messageToUser);

        when(this.service.registerUser(user)).thenReturn(createdResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                        .equals(createdResponse);

    }
   @Test
    void testNonCanadianIpAddress() throws Exception {
       String errorMessage = "Only Canadian IP are allowed";
       ResponseEntity<String> nonCaResp = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        user.setIpAddress("1.0.0.0");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                        .equals(nonCaResp);


    }
    @Test
    void testBlankUserName() throws Exception {
        user.setUserName(" ");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyUserName() throws Exception {
        user.setUserName("");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBlankPassword() throws Exception {
        user.setPassword(" ");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyPassword() throws Exception {
        user.setPassword("");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBlankIpAddress() throws Exception {
        user.setIpAddress(" ");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyIpAddress() throws Exception {
        user.setIpAddress("");
        when(this.service.registerUser(user)).thenReturn(badReqResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        .content(asJsonString(user)))
                .andExpect(status().isBadRequest());
    }
}
