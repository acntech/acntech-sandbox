package no.acntech.sandbox.model;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Table(name = "GREETINGS")
@Entity
public class GreetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @CreationTimestamp
    private Instant created;

    public GreetingEntity() {
    }

    public GreetingEntity(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreated() {
        return created;
    }
}
