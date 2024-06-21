package prog24hour.prog24hourbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prog24hour.prog24hourbackend.dto.AgeGroupDto;
import prog24hour.prog24hourbackend.service.AgeGroupService;

import java.util.List;

@Controller
@RequestMapping("/api/age-group")
public class AgeGroupController {

    private final AgeGroupService ageGroupService;

    public AgeGroupController(AgeGroupService ageGroupService) {
        this.ageGroupService = ageGroupService;
    }

    @GetMapping
    public ResponseEntity<List<AgeGroupDto>> getAllAgeGroups() {
        return ResponseEntity.ok(ageGroupService.getAllAgeGroups());
    }
}
