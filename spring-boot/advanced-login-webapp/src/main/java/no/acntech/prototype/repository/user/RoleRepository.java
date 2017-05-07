package no.acntech.prototype.repository.user;

import no.acntech.prototype.entity.user.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
