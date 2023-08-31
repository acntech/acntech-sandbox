package no.acntech.sandbox.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "PREFIXES")
@Entity
public class PrefixEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nameId;
    @Column(nullable = false)
    private String prefix;

    public PrefixEntity() {
    }

    public PrefixEntity(Long nameId, String prefix) {
        this.nameId = nameId;
        this.prefix = prefix;
    }

    public Long getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }
}
