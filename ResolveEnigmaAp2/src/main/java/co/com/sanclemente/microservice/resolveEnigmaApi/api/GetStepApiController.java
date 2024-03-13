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

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GetStepApiController implements GetStepApi {

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<JsonApiBodyResponseSuccess>> getStep(@ApiParam(value = "request body get enigma step", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
        List<GetEnigmaRequest> enigmas = body.getData();
        List<JsonApiBodyResponseSuccess> responseList = new ArrayList<>();

        for (GetEnigmaRequest enigma : enigmas) {
        	
            Header header = enigma.getHeader();
            String id = header.getId();
            String type = header.getType();
            String enigmaQuestion = enigma.getEnigma();

            String solution = solveEnigma(enigmaQuestion);

            GetEnigmaStepResponse enigmaStepResponse = new GetEnigmaStepResponse();
            enigmaStepResponse.setId(id);
            enigmaStepResponse.setType(type);
            enigmaStepResponse.setSolution(solution);

            JsonApiBodyResponseSuccess responseBody = new JsonApiBodyResponseSuccess();
            responseBody.addDataItem(enigmaStepResponse);
            responseList.add(responseBody);
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    
    @GetMapping("/get")
    public ResponseEntity<String> getEnigma() {
        return new ResponseEntity<String>("Step2: Put the giraffe in", HttpStatus.OK);
    }
 
    private String solveEnigma(String enigmaQuestion) {
        return "Step2: Put the giraffe in";
        //-   - Step3: Close the door
    }
}
