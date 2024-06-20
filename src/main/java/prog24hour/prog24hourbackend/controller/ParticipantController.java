package prog24hour.prog24hourbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import prog24hour.prog24hourbackend.dto.ParticipantDto;
import prog24hour.prog24hourbackend.service.ParticipantService;

@Controller
@RequestMapping("/api/participant")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
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
}
