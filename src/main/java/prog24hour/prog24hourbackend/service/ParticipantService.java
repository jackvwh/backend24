package prog24hour.prog24hourbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import prog24hour.prog24hourbackend.dto.ParticipantDto;
import prog24hour.prog24hourbackend.entity.AgeGroup;
import prog24hour.prog24hourbackend.entity.Club;
import prog24hour.prog24hourbackend.entity.GenderType;
import prog24hour.prog24hourbackend.entity.Participant;
import prog24hour.prog24hourbackend.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final GenderTypeRepository genderTypeRepository;
    private final ClubRepository clubRepository;
    private final AgeGroupRepository ageGroupRepository;
    private final DisciplineRepository disciplineRepository;

    public ParticipantService(ParticipantRepository participantRepository, GenderTypeRepository genderTypeRepository, ClubRepository clubRepository, DisciplineRepository disciplineRepository, AgeGroupRepository ageGroupRepository) {
        this.participantRepository = participantRepository;
        this.genderTypeRepository = genderTypeRepository;
        this.clubRepository = clubRepository;
        this.disciplineRepository = disciplineRepository;
        this.ageGroupRepository = ageGroupRepository;
    }

    public ParticipantDto getParticipant(Integer id) {
        return new ParticipantDto(participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find participant")));
    }

    public List<ParticipantDto> getAllParticipants() {
        return participantRepository.findAll().stream().map(ParticipantDto::new).collect(Collectors.toList());
    }

    public ParticipantDto createParticipant(ParticipantDto dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the id for a new participant");
        }

        try {
            GenderType genderType = genderTypeRepository.findByName(dto.getGender()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find gender type"));
            Club club = clubRepository.findById(dto.getClub().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find club"));

            Participant newParticipant = new Participant(dto);
            newParticipant.setGender(genderType);
            newParticipant.setClub(club);

            // Set the age group based on the age
            AgeGroup ageGroup = getAgeGroup(newParticipant.getAge());
            newParticipant.setAgeGroup(ageGroup);

            // loop through the list of disciplines and add them to the participant
            dto.getDisciplines().forEach(discipline -> {
                newParticipant.addDiscipline(disciplineRepository.findById(discipline.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find discipline")));
            });

            // save the participant
            return new ParticipantDto(participantRepository.save(newParticipant));
        } catch ( Exception e) {
            log.error("Error while saving participant: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving participant");
        }
    }

    public ParticipantDto updateParticipant (Integer id, ParticipantDto dto) {
        if (dto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must provide the id for an existing participant");
        }

        try {
            Participant participant = participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find participant"));

            GenderType genderType = genderTypeRepository.findByName(dto.getGender()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find gender type"));
            Club club = clubRepository.findById(dto.getClub().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find club"));

            participant.setFirstName(dto.getFirstName());
            participant.setLastName(dto.getLastName());
            participant.setBirthDate(dto.getBirthDate());
            participant.setEmail(dto.getEmail());
            participant.setPhone(dto.getPhone());
            participant.setGender(genderType);
            participant.setClub(club);

            // Set the age group based on the age
            AgeGroup ageGroup = getAgeGroup(participant.getAge());
            participant.setAgeGroup(ageGroup);

            // remove all disciplines to avoid duplicates and hanging disciplines
            participant.removeAllDisciplines();

            // loop through the list of disciplines and add them to the participant
            dto.getDisciplines().forEach(discipline -> {
                participant.addDiscipline(disciplineRepository.findById(discipline.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find discipline")));
            });

            // save the participant
            return new ParticipantDto(participantRepository.save(participant));
        } catch ( Exception e) {
            log.error("Error while updating participant: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating participant");
        }
    }

    public void deleteParticipant(Integer id) {
        try {
            participantRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error while deleting participant: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while deleting participant");
        }
    }

    public void addDisciplineToParticipant(Integer participantId, Integer disciplineId) {
        try {
            Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find participant"));

            participant.addDiscipline(disciplineRepository.findById(disciplineId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find discipline")));

            participantRepository.save(participant);
        } catch (Exception e) {
            log.error("Error while adding discipline to participant: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while adding discipline to participant");

        }
    }

    public void removeDisciplineFromParticipant(Integer participantId, Integer disciplineId) {
        try {
            Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find participant"));
            participant.getDisciplines().removeIf(discipline -> discipline.getId().equals(disciplineId));
            participantRepository.save(participant);
        } catch (Exception e) {
            log.error("Error while removing discipline from participant: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while removing discipline from participant");
        }
    }

    public AgeGroup getAgeGroup(Integer age) {
       try {
          return ageGroupRepository.findByMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(age, age).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find age group"));
       } catch (Exception e) {
           log.error("Error while getting age group: {}", e.getMessage());
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while getting age group");
       }
    }
}
