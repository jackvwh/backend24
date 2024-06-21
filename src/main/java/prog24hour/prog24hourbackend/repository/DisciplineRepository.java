package prog24hour.prog24hourbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog24hour.prog24hourbackend.entity.Discipline;
import prog24hour.prog24hourbackend.entity.Participant;

import java.util.Set;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
}
