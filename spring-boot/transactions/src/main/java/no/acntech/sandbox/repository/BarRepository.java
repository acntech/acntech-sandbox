package no.acntech.sandbox.repository;

import no.acntech.sandbox.domain.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<Bar, Long> {

}
