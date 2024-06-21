package prog24hour.prog24hourbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import prog24hour.prog24hourbackend.dto.ParticipantDto;
import prog24hour.prog24hourbackend.dto.ResultDto;
import prog24hour.prog24hourbackend.service.ParticipantService;
import prog24hour.prog24hourbackend.service.ResultService;

@Controller
@RequestMapping("/api/participant")
public class ParticipantController {

    private final ParticipantService participantService;
    public final ResultService resultService;

    public ParticipantController(ParticipantService participantService, ResultService resultService) {
        this.participantService = participantService;
        this.resultService = resultService;
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> createParticipant(@RequestBody ParticipantDto participantDto) {
        return ResponseEntity.ok(participantService.createParticipant(participantDto));
    }

    @GetMapping
    public ResponseEntity<Iterable<ParticipantDto>> getParticipants() {
        return ResponseEntity.ok(participantService.getAllParticipants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> getParticipant(@PathVariable Integer id) {
        return ResponseEntity.ok(participantService.getParticipant(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDto> updateParticipant(@PathVariable Integer id, @RequestBody ParticipantDto participantDto) {
        return ResponseEntity.ok(participantService.updateParticipant(id, participantDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParticipant(@PathVariable Integer id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.ok("Participant deleted");
    }

    // mappings for results
    @GetMapping("/{id}/results")
    public ResponseEntity<Iterable<ResultDto>> getResultsForParticipant(@PathVariable Integer id) {
        return ResponseEntity.ok(resultService.getResultsByParticipant(id));
    }

    @GetMapping("/results")
    public ResponseEntity<Iterable<ResultDto>> getResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @GetMapping("/results/{disciplineId}")
    public ResponseEntity<Iterable<ResultDto>> getResultsForDiscipline(@PathVariable Integer disciplineId) {
        return ResponseEntity.ok(resultService.getResultsByDiscipline(disciplineId));
    }

    @PostMapping("/results")
    public ResponseEntity<ResultDto> createResult(@RequestBody ResultDto resultDto) {
        return ResponseEntity.ok(resultService.createResult(resultDto));
    }

    @PutMapping("/results/{id}")
    public ResponseEntity<ResultDto> updateResult(@PathVariable Integer id, @RequestBody ResultDto resultDto) {
        return ResponseEntity.ok(resultService.updateResult(id, resultDto));
    }

    @DeleteMapping("/results/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable Integer id) {
        resultService.deleteResult(id);
        return ResponseEntity.ok("Result deleted");
    }
}
