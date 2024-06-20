package prog24hour.prog24hourbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import prog24hour.prog24hourbackend.dto.DisciplineDto;
import prog24hour.prog24hourbackend.service.DisciplineService;

import java.util.List;

@Controller
@RequestMapping("/api/discipline")
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public ResponseEntity<List<DisciplineDto>> getAllDisciplines() {
        return ResponseEntity.ok(disciplineService.getAllDisciplines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDto> getDiscipline(@PathVariable Integer id) {
        return ResponseEntity.ok(disciplineService.getDiscipline(id));
    }

    @PostMapping
    public ResponseEntity<DisciplineDto> createDiscipline(@RequestBody DisciplineDto dto) {
        return ResponseEntity.ok(disciplineService.createDiscipline(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineDto> updateDiscipline(@RequestBody DisciplineDto dto) {
        return ResponseEntity.ok(disciplineService.updateDiscipline(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Integer id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.ok().build();
    }
}
