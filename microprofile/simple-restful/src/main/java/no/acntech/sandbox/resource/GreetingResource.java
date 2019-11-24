package no.acntech.sandbox.resource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import no.acntech.sandbox.domain.Greeting;

@Path("greetings")
@RequestScoped
public class GreetingResource {

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting get(@PathParam(value = "name") String name) {
        return new Greeting("Hello " + name + "!");
    }
}
