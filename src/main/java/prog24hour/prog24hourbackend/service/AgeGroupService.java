package prog24hour.prog24hourbackend.service;

import org.springframework.stereotype.Service;
import prog24hour.prog24hourbackend.dto.AgeGroupDto;
import prog24hour.prog24hourbackend.entity.AgeGroup;
import prog24hour.prog24hourbackend.repository.AgeGroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgeGroupService {

    private final AgeGroupRepository ageGroupRepository;

    public AgeGroupService(AgeGroupRepository ageGroupRepository) {
        this.ageGroupRepository = ageGroupRepository;
    }

    public List<AgeGroupDto> getAllAgeGroups() {
        try {
            List<AgeGroup> ageGroups = ageGroupRepository.findAll();
            return ageGroups.stream()
                    .map(AgeGroupDto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Could not get age groups");
        }
    }
}
