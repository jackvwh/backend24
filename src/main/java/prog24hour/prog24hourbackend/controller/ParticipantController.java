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
    public ResponseEntity<ParticipantDto> createParticipant(ParticipantDto participantDto) {
        return ResponseEntity.ok(participantService.createParticipant(participantDto));
    }

    @GetMapping
    public ResponseEntity<Iterable<ParticipantDto>> getParticipants() {
        return ResponseEntity.ok(participantService.getAllParticipants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> getParticipant(Integer id) {
        return ResponseEntity.ok(participantService.getParticipant(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDto> updateParticipant(Integer id, ParticipantDto participantDto) {
        return ResponseEntity.ok(participantService.updateParticipant(id, participantDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParticipant(Integer id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.ok("Participant deleted");
    }
}
