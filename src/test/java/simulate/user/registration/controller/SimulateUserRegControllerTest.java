package simulate.user.registration.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SimulateUserRegController.class)
@AutoConfigureMockMvc
public class SimulateUserRegControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void testBlankUserName() throws Exception {
        ResponseEntity<String> notFoundResponse = new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/registration/{username}/{password}/{ipAddress}", " ", "pass", "127.0.0.1")
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyUserName() throws Exception {
        ResponseEntity<String> notFoundResponse = new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/registration/{username}/{password}/{ipAddress}",  "","pass", "127.0.0.1")
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testBlankPassword() throws Exception {
        ResponseEntity<String> notFoundResponse = new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/registration/{username}/{password}/{ipAddress}", "username", " ", "127.0.0.1")
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyPassword() throws Exception {
        ResponseEntity<String> notFoundResponse = new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/registration/{username}/{password}/{ipAddress}",  "username","", "127.0.0.1")
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testBlankIpAddress() throws Exception {
        ResponseEntity<String> notFoundResponse = new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/registration/{username}/{password}/{ipAddress}", "username", "password", " ")
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyIpAddress() throws Exception {
        ResponseEntity<String> notFoundResponse = new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/registration/{username}/{password}/{ipAddress}",  "username","password", "")
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

}
