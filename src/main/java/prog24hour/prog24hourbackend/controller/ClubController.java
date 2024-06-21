package prog24hour.prog24hourbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prog24hour.prog24hourbackend.dto.ClubDto;
import prog24hour.prog24hourbackend.service.ClubService;

import java.util.List;

@Controller
@RequestMapping("/api/club")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<ClubDto>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }
}
