package no.acntech.sandbox.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import no.acntech.sandbox.consumer.GreetingSoapConsumer;
import no.acntech.sandbox.domain.Greeting;

@Path("greetings")
@RequestScoped
public class GreetingResource {

    @Inject
    GreetingSoapConsumer greetingSoapConsumer;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting get(@QueryParam("name") String name) {
        return greetingSoapConsumer.getGreeting(name != null ? name : "Nobody");
    }
}
