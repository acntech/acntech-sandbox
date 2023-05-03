package no.acntech.sandbox.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.acntech.sandbox.model.AuthorEntity;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    AuthorEntity findByFirstNameOrLastName(String firstName, String lastName);
}
