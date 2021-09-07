package no.acntech.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import no.acntech.sandbox.domain.Greeting;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {

}
