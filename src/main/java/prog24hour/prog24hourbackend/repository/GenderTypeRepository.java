package prog24hour.prog24hourbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog24hour.prog24hourbackend.entity.GenderType;

import java.util.Optional;

@Repository
public interface GenderTypeRepository extends JpaRepository<GenderType, Integer> {
    Optional<GenderType> findByName(String name);
}
