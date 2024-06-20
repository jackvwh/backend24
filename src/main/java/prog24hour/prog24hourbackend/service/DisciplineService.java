package prog24hour.prog24hourbackend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import prog24hour.prog24hourbackend.dto.DisciplineDto;
import prog24hour.prog24hourbackend.entity.Discipline;
import prog24hour.prog24hourbackend.repository.DisciplineRepository;
import prog24hour.prog24hourbackend.repository.ResultTypeRepository;

import java.util.List;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final ResultTypeRepository resultTypeRepository;

    public DisciplineService(DisciplineRepository disciplineRepository, ResultTypeRepository resultTypeRepository) {
        this.disciplineRepository = disciplineRepository;
        this.resultTypeRepository = resultTypeRepository;
    }

    public List<DisciplineDto> getAllDisciplines() {
        try {
            List<Discipline> disciplines = disciplineRepository.findAll();
            // Convert the list of disciplines to a list of discipline dtos
            return disciplines.stream().map(DisciplineDto::new).toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while fetching disciplines");
        }
    }

    public DisciplineDto getDiscipline(Integer id) {
        try {
            Discipline discipline = disciplineRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find discipline"));
            ;return new DisciplineDto(discipline);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while fetching discipline");
        }
    }

    public DisciplineDto createDiscipline(DisciplineDto dto) {
        try {
            if (dto.getId() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the id for a new discipline");
            }
            Discipline discipline = new Discipline(dto);
            discipline.setResultType(resultTypeRepository.findById(dto.getResultType().getId()).orElseThrow(() -> new RuntimeException("Cannot find result type")));
            return new DisciplineDto(disciplineRepository.save(discipline));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving discipline");
        }
    }

    public DisciplineDto updateDiscipline(DisciplineDto dto) {
        try {
            if (dto.getId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must provide the id for the discipline");
            }
            Discipline discipline = disciplineRepository.findById(dto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find discipline"));
            discipline.setName(dto.getName());
            discipline.setDescription(dto.getDescription());
            discipline.setResultType(resultTypeRepository.findById(dto.getResultType().getId()).orElseThrow(() -> new RuntimeException("Cannot find result type")));
            return new DisciplineDto(disciplineRepository.save(discipline));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating discipline");
        }
    }

    public void deleteDiscipline(Integer id) {
        try {
            Discipline discipline = disciplineRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find discipline"));
            disciplineRepository.delete(discipline);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while deleting discipline");
        }
    }
}
