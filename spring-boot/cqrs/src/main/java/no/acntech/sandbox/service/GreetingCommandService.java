package no.acntech.sandbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.acntech.sandbox.model.GreetingEvent;
import no.acntech.sandbox.producer.GreetingProducer;

@Service
public class GreetingCommandService {

    private final GreetingProducer greetingProducer;

    @Autowired
    public GreetingCommandService(final GreetingProducer greetingProducer) {
        this.greetingProducer = greetingProducer;
    }

    public void createGreeting(final GreetingEvent greeting) {
        greetingProducer.publish(greeting);
    }
}
