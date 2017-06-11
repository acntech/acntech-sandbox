package no.acntech.sandbox.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.acntech.sandbox.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
