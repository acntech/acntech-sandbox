package no.acntech.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import no.acntech.sandbox.model.NameEntity;

public interface NameRepository extends JpaRepository<NameEntity, Long> {
}
