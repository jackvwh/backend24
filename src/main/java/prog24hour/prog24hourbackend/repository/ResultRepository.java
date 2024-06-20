package prog24hour.prog24hourbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog24hour.prog24hourbackend.entity.Participant;
import prog24hour.prog24hourbackend.entity.Result;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    Collection<Result> findByParticipantId(Integer id);

    Collection<Result> findByDisciplineId(Integer disciplineId);
}
