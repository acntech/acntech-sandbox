package no.acntech.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import no.acntech.sandbox.model.QueryEntity;

public interface QueryRepository extends JpaRepository<QueryEntity, Long> {

    Optional<QueryEntity> findByName(String name);
}
