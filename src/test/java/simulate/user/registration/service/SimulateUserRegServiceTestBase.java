package simulate.user.registration.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SimulateUserRegServiceTestBase {

    protected ResponseEntity<String> getGeoLocationMockCaResponse(){
        String stringJsonResp = "{\"status\":\"success\",\"country\":\"Canada\",\"countryCode\":\"CA\",\"region\":\"NS\"," +
                "\"regionName\":\"Nova Scotia\",\"city\":\"Bedford\",\"zip\":\"B4A\",\"lat\":44.7264,\"lon\":-63.6621," +
                "\"timezone\":\"America/Halifax\",\"isp\":\"EastLink\",\"org\":\"Eastlink HSI\",\"as\":\"AS11260 EastLink\"," +
                "\"query\":\"24.215.85.18\"}\n";

         return new ResponseEntity<>(stringJsonResp, HttpStatus.OK);
    }

    protected ResponseEntity<String> getGeoLocationMockNonCaResponse(){
        String stringJsonResp = "{\"status\":\"success\",\"country\":\"Australia\",\"countryCode\":\"AU\",\"region\":\"QLD\",\"regionName\":\"Queensland\"," +
                "\"city\":\"South Brisbane\",\"zip\":\"4101\",\"lat\":-27.4766,\"lon\":153.0166,\"timezone\":\"Australia/Brisbane\"," +
                "\"isp\":\"Cloudflare, Inc\",\"org\":\"APNIC and Cloudflare DNS Resolver project\",\"as\":\"AS13335 Cloudflare, Inc.\"," +
                "\"query\":\"1.0.0.0\"}\n";

        return new ResponseEntity<>(stringJsonResp, HttpStatus.OK);
    }


}
