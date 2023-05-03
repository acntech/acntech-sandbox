package no.acntech.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import no.acntech.sandbox.model.FooEntity;

public interface FooRepository extends JpaRepository<FooEntity, Long> {

}
