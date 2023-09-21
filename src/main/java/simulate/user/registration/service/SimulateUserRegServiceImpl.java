package simulate.user.registration.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simulate.user.registration.model.User;
import simulate.user.registration.utils.InputValidator;

import java.util.Random;


@Service
@Slf4j
public class SimulateUserRegServiceImpl implements SimulateUserRegService{
    @Value( "${geolocation.url}")
    private String geolocationUrl;
    //@Autowired
    private InputValidator inputValidator;
    @Autowired
    RestTemplateBuilder builder;
    public SimulateUserRegServiceImpl() {
        this.inputValidator = new InputValidator();
    }

    @Override
    public ResponseEntity<String> registerUser(User user) {
        HttpStatus status = HttpStatus.CREATED;
        if(inputValidator.isValidCredentials(user) && inputValidator.isValidIpAddress(user)) {
            String messageToUser = new Random().nextInt() + " " + user.getUserName();
            String city = getUserCity(user.getIpAddress());
            if (city.equalsIgnoreCase("Only Canadian IP are allowed")) {
                messageToUser += " we are sorry, only Canadian IP are allowed";
                status = HttpStatus.BAD_REQUEST;
            } else if (city == null || city.isEmpty() || city.isBlank()) {
                messageToUser = " we are unable to verify your IP address";
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }else{
                messageToUser += " From " + city
                    + "\n" + "Registration Successfully Completed";
        }
            return ResponseEntity.status(status).body(messageToUser);
        }else{
            String errorMessage = "";
            if(!inputValidator.isValidCredentials(user)){
                errorMessage = "Invalid Credentials";
            }else{
                errorMessage = "Invalid IP address";
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    private String getUserCity(String ipAddress){
        geolocationUrl += ipAddress;
        String city = "";
        String country = "";
        try {
            RestTemplate rest = builder.build();
            ResponseEntity<String> resp = rest.getForEntity(geolocationUrl, String.class);
            if (resp != null) {
                country = getFieldValueByFieldName(this.toJson(resp.getBody()), "country");
            }
            if (!country.isEmpty() && country.equalsIgnoreCase("Canada")) {
                city = getFieldValueByFieldName(this.toJson(resp.getBody()), "city");
            }else{
                return "Only Canadian IP are allowed";
            }
        }catch(Exception e){
            log.error("Error while calling Geolocation with {}" + ipAddress);
            log.error("Error  {}" + e);
        }
      return city;
    }

    private String getFieldValueByFieldName(JsonNode respBody, String fieldName){
        String fieldValue = "";
            if(respBody != null && respBody.has(fieldName) && respBody.get(fieldName) != null && !respBody.get(fieldName) .asText().isEmpty()){
                fieldValue = respBody.get(fieldName) .asText();
            }
        return fieldValue;
    }

    private JsonNode toJson(String respBody){
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getJsonFactory();
        JsonNode jsonResp = null;
        try {
            JsonParser parser = factory.createJsonParser(respBody);
            jsonResp = mapper.readTree(parser);
            log.info("Object: "+jsonResp);
        }
        catch(Exception e) {
            log.error("Json parsing Error: "+e);
        }
        return  jsonResp;
    }
}
