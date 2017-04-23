package no.acntech.prototype.domain.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.acntech.prototype.domain.user.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
