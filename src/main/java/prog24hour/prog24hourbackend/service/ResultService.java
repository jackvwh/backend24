package prog24hour.prog24hourbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import prog24hour.prog24hourbackend.dto.ResultDto;
import prog24hour.prog24hourbackend.entity.Discipline;
import prog24hour.prog24hourbackend.entity.Participant;
import prog24hour.prog24hourbackend.entity.Result;
import prog24hour.prog24hourbackend.repository.DisciplineRepository;
import prog24hour.prog24hourbackend.repository.ParticipantRepository;
import prog24hour.prog24hourbackend.repository.ResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;

    public ResultService(ResultRepository resultRepository, ParticipantRepository participantRepository, DisciplineRepository disciplineRepository) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public List<ResultDto> getResultsByParticipant(Integer participantId) {
        return resultRepository.findByParticipantId(participantId).stream().map(ResultDto::new).collect(Collectors.toList());
    }

    public List<ResultDto> getResultsByDiscipline(Integer disciplineId) {
        return resultRepository.findByDisciplineId(disciplineId).stream().map(ResultDto::new).collect(Collectors.toList());
    }

    public ResultDto createResult(ResultDto dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the id for a new result");
        }
        try {
            Participant participant = participantRepository.findById(dto.getParticipant().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find participant"));
            Discipline discipline = disciplineRepository.findById(dto.getDiscipline().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find discipline"));

            Result result = new Result(dto);
            result.setParticipant(participant);
            result.setDiscipline(discipline);

            return new ResultDto(resultRepository.save(result));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving result");
        }
    }

    public ResultDto updateResult(Integer id, ResultDto dto) {
        if (dto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must provide the id for the result");
        }
        try {
            Result result = resultRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find result"));

            Participant participant = participantRepository.findById(dto.getParticipant().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find participant"));

            Discipline discipline = disciplineRepository.findById(dto.getDiscipline().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find discipline"));

            result.setResultDate(dto.getResultDate());
            result.setResultValue(dto.getResultValue());
            result.setParticipant(participant);
            result.setDiscipline(discipline);

            return new ResultDto(resultRepository.save(result));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving result");
        }
    }

    public void deleteResult(Integer id) {
        try {
            resultRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while deleting result");
        }
    }
}
