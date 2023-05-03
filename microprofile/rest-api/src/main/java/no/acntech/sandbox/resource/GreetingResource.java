package no.acntech.sandbox.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import no.acntech.sandbox.model.Greeting;

@Path("greetings")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting get(@QueryParam("name") String name) {
        return new Greeting("Hello " + (name != null ? name : "Nobody") + "!");
    }
}
