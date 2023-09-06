package no.acntech.sandbox.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import no.acntech.sandbox.model.GreetingDto;

@Path("greetings")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GreetingDto get(@QueryParam("name") String name) {
        return new GreetingDto("Hello " + (name != null ? name : "Nobody") + "!");
    }
}
