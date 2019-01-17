package no.acntech.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import no.acntech.sandbox.domain.Foo;

public interface FooRepository extends JpaRepository<Foo, Long> {

}
