package no.acntech.sandbox.webservice.endpoint;

import no.acntech.sandbox.webservice.simple.v1.SimplePortType;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class SimpleWebService implements SimplePortType {

    @Override
    public String sayHello(String firstName) {
        return "Hello " + firstName + "!";
    }
}
