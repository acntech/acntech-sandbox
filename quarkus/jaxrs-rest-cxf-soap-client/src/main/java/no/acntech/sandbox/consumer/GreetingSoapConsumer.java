package no.acntech.sandbox.consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import no.acntech.sandbox.model.Greeting;
import no.acntech.sandbox.webservice.greeting.v1.GreetingPortType;

@ApplicationScoped
public class GreetingSoapConsumer {

    @Inject
    GreetingPortType greetingPortType;

    public Greeting getGreeting(String name) {
        String greeting = greetingPortType.sayHello(name);
        return new Greeting(greeting);
    }
}
