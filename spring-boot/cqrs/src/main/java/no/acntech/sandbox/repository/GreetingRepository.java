package no.acntech.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

import no.acntech.sandbox.model.GreetingEntity;

@Repository
public interface GreetingRepository extends JpaRepository<GreetingEntity, Long> {

    List<GreetingEntity> findAllByOrderByCreatedDesc();

    List<GreetingEntity> findAllByCreatedAfterOrderByCreatedDesc(final Instant created);
}
