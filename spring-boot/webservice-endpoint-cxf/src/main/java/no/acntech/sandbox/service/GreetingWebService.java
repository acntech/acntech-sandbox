package no.acntech.sandbox.service;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

import no.acntech.sandbox.webservice.greeting.v1.GreetingPortType;
import no.acntech.sandbox.webservice.greeting.v1.types.SayHelloRequestType;
import no.acntech.sandbox.webservice.greeting.v1.types.SayHelloResponseType;

@Endpoint
public class GreetingWebService implements GreetingPortType {

    @Override
    public SayHelloResponseType sayHello(final SayHelloRequestType request) {
        SayHelloResponseType response = new SayHelloResponseType();
        response.setGreeting("Hello " + request.getFirstName() + "!");
        return response;
    }
}
