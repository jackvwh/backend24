package prog24hour.prog24hourbackend.security.repository;

import saxxen.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
