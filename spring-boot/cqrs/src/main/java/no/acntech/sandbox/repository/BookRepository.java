package no.acntech.sandbox.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.acntech.sandbox.model.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {

}
