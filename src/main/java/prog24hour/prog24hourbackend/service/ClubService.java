package prog24hour.prog24hourbackend.service;

import org.springframework.stereotype.Service;
import prog24hour.prog24hourbackend.dto.ClubDto;
import prog24hour.prog24hourbackend.entity.Club;
import prog24hour.prog24hourbackend.repository.ClubRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<ClubDto> getAllClubs() {
        try {
           List<Club> clubs = clubRepository.findAll();
              return clubs.stream()
                     .map(ClubDto::new)
                     .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Could not get clubs");
        }
    }
}
