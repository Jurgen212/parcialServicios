package co.com.sanclemente.microservice.resolveEnigmaApi.api;

import co.com.sanclemente.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.sanclemente.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.sanclemente.microservice.resolveEnigmaApi.model.Header;
import co.com.sanclemente.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.sanclemente.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GetStepApiController implements GetStepApi {

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
    @GetMapping("/get")
    public ResponseEntity<JsonApiBodyResponseSuccess> getStep() {
        
        GetEnigmaStepResponse stepResponse = new GetEnigmaStepResponse();
        stepResponse.setId("1");
        stepResponse.setType("Enigma");
        stepResponse.setAnswer("Jurgen Sanclemente");

        
        JsonApiBodyResponseSuccess responseBody = new JsonApiBodyResponseSuccess();
        responseBody.addDataItem(stepResponse);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }   
    
    
    
    @GetMapping("/getAll")
    public ResponseEntity<List<JsonApiBodyResponseSuccess>> getAll() {
        
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<String> response1 = restTemplate.getForEntity( "http://localhost:8080/v1/getOneEnigma/get", String.class ); 
    	ResponseEntity<String> response2 = restTemplate.getForEntity( "http://localhost:8081/v1/getOneEnigma/get", String.class );
    	ResponseEntity<String> response3 = restTemplate.getForEntity( "http://localhost:8082/v1/getOneEnigma/get", String.class );
    	String responseFinal = response1.getBody() + "  " + response2.getBody() + "  " + response3.getBody();
    	
    	GetEnigmaStepResponse responseEnigma = new GetEnigmaStepResponse();
    	JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
    	
    	List<JsonApiBodyResponseSuccess> responseSuccessList = new ArrayList<JsonApiBodyResponseSuccess>();
    	
    	Header header = new Header();
    	
    	header.id("12345");
    	header.setType("Enigma");
    	responseEnigma.setHeader( header );
    	responseEnigma.setAnswer( responseFinal );
    	
    	responseSuccess.addDataItem(responseEnigma);
    	responseSuccessList.add(responseSuccess);
    	
    	
    	
    	
        return new ResponseEntity<List<JsonApiBodyResponseSuccess>>( responseSuccessList , HttpStatus.OK );
    }      
}
