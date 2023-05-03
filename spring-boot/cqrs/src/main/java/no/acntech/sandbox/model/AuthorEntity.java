package no.acntech.sandbox.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "AUTHORS")
@Entity
public class AuthorEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String biography;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_ID_SEQ")
    @SequenceGenerator(sequenceName = "AUTHOR_ID_SEQ", allocationSize = 1, name = "AUTHOR_ID_SEQ")
    @Id
    @Column(name = "AUTHOR_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
