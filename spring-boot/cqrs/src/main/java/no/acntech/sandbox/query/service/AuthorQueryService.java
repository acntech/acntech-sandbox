package no.acntech.sandbox.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.acntech.sandbox.model.AuthorEntity;
import no.acntech.sandbox.repository.AuthorRepository;

@Service
public class AuthorQueryService {

    private final AuthorRepository repository;

    @Autowired
    public AuthorQueryService(final AuthorRepository repository) {
        this.repository = repository;
    }

    public AuthorEntity getAuthor(String firstName, String lastName) {
        return repository.findByFirstNameOrLastName(firstName, lastName);
    }

    public Iterable<AuthorEntity> findAuthors() {
        return repository.findAll();
    }
}
