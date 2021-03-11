package no.acntech.sandbox.service;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

import no.acntech.sandbox.webservice.greeting.v1.GreetingPortType;

@Endpoint
public class GreetingWebService implements GreetingPortType {

    @Override
    public String sayHello(String firstName) {
        return "Hello " + firstName + "!";
    }
}
