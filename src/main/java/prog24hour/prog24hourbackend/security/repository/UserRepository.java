package prog24hour.prog24hourbackend.security.repository;

import saxxen.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM users u WHERE u.resident.email = ?1")
    Optional<User> findByEmail(String email);
}
