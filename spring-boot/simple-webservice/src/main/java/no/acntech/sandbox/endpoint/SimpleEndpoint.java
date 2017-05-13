package no.acntech.sandbox.endpoint;

import no.acntech.sandbox.webservice.simple.wsdl.SimplePortType;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class SimpleEndpoint implements SimplePortType {

    @Override
    public String sayHello(String firstName) {
        return "Hello " + firstName + "!";
    }
}
