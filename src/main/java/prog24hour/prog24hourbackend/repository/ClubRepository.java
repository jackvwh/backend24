package prog24hour.prog24hourbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog24hour.prog24hourbackend.entity.Club;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
    Optional<Club> findById(Integer id);
}
