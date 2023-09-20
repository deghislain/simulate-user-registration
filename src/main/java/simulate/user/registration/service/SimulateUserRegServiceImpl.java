package simulate.user.registration.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@Slf4j
public class SimulateUserRegServiceImpl implements SimulateUserRegService{
    private final RestTemplate rest;
    @Value( "${geolocation.url}")
    private String geolocationUrl;

    public SimulateUserRegServiceImpl(RestTemplateBuilder builder) {
        this.rest = builder.build();
    }

    @Override
    public ResponseEntity<String> registerUser(String username, String password, String ipAddress) {

        this.getUserCity(ipAddress);
        return null;
    }

    private String getUserCity(String ipAddress){
        geolocationUrl += ipAddress;
        String city = "";
        String country = "";
        try {
            ResponseEntity<String> resp = rest.getForEntity(geolocationUrl, String.class);
            if (resp != null) {
                country = getFieldValueByFieldName(this.toJson(resp.getBody()), "country");
            }
            if (!country.isEmpty() && country.equalsIgnoreCase("Canada")) {
                city = getFieldValueByFieldName(this.toJson(resp.getBody()), "city");
            }
        }catch(Exception e){
            log.error("Error while calling Geolocation with {}" + ipAddress);
            log.error("Error  {}" + e);
        }
      return city;
    }

    private String getFieldValueByFieldName(JsonNode respBody, String fieldName){
        String fieldValue = "";
        if(respBody != null){
            if(respBody.has(fieldName) && respBody.get(fieldName) != null && !respBody.get(fieldName) .asText().isEmpty()){
                fieldValue = respBody.get(fieldName) .asText();
            }
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
