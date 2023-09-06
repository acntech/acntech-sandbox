package no.acntech.sandbox.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.GreetingDto;

@RequestMapping(path = "/api/greetings")
@RestController
public class GreetingResource {

    @Operation(summary = "Get greeting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Greeting response",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GreetingDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<GreetingDto> get(@RequestParam(name = "name", defaultValue = "Nobody") String name) {
        return ResponseEntity.ok(new GreetingDto("Hello " + name + "!"));
    }
}
