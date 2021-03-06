package no.acntech.sandbox.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fooId;
    private String data;

    public Long getId() {
        return id;
    }

    public Long getFooId() {
        return fooId;
    }

    public void setFooId(Long fooId) {
        this.fooId = fooId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
