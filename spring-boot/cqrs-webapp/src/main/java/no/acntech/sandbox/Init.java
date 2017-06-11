package no.acntech.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import no.acntech.sandbox.consumer.service.AuthorConsumerService;
import no.acntech.sandbox.entity.Author;

@Component
public class Init {

    private final AuthorConsumerService service;

    @Autowired
    public Init(AuthorConsumerService service) {
        this.service = service;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Author author1 = new Author();
        author1.setFirstName("Franz");
        author1.setLastName("Kafka");

        service.createAuthor(author1);

        Author author2 = new Author();
        author2.setFirstName("Alexandre");
        author2.setLastName("Dumas");

        service.createAuthor(author2);
    }
}
