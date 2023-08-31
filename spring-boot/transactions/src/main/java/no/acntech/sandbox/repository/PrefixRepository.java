package no.acntech.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import no.acntech.sandbox.model.PrefixEntity;

public interface PrefixRepository extends JpaRepository<PrefixEntity, Long> {

    PrefixEntity findByNameId(Long nameId);
}
