package prog24hour.prog24hourbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog24hour.prog24hourbackend.entity.AgeGroup;

@Repository
public interface AgeGroupRepository extends JpaRepository<AgeGroup, Integer> {
}
