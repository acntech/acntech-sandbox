package no.acntech.sandbox.repository;

import no.acntech.sandbox.model.BarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<BarEntity, Long> {

}
