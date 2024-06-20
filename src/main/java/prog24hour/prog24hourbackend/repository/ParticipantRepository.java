package prog24hour.prog24hourbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog24hour.prog24hourbackend.entity.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    boolean findByEmail(String email);
}
