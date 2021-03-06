package no.acntech.sandbox.consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import no.acntech.sandbox.domain.Greeting;
import no.acntech.sandbox.webservice.simple.v1.SimplePortType;
import no.acntech.sandbox.webservice.simple.v1.SimpleService;

@ApplicationScoped
public class GreetingSoapConsumer {

    public Greeting getGreeting(String name) {
        SimpleService service = new SimpleService();
        SimplePortType port = service.getSimplePort();
        String greeting = port.sayHello(name);
        return new Greeting(greeting);
    }
}
