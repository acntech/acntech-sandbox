package no.acntech.sandbox.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.acntech.sandbox.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findByFirstNameOrLastName(String firstName, String lastName);
}
