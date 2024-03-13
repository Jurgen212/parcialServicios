package co.com.sanclemente.microservice.resolveEnigmaApi.api;

import co.com.sanclemente.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.sanclemente.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.sanclemente.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "getStep", description = "the getStep API")
public interface GetStepApi {

    @ApiOperation(value = "Get one enigma step API", nickname = "getStep", notes = "Get one enigma step API", response = JsonApiBodyResponseSuccess.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "search results matching criteria", response = JsonApiBodyResponseSuccess.class, responseContainer = "List"),
            @ApiResponse(code = 424, message = "bad input parameter", response = JsonApiBodyResponseErrors.class, responseContainer = "List")
    })
    @GetMapping("/get")
    ResponseEntity<JsonApiBodyResponseSuccess> getStep();
}
