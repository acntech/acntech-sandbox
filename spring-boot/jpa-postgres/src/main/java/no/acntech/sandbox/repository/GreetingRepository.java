package no.acntech.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import no.acntech.sandbox.model.GreetingEntity;

public interface GreetingRepository extends JpaRepository<GreetingEntity, Long> {

}
