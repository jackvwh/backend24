package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.dto.AgeGroupDto;

@Data
@Entity
@NoArgsConstructor
public class AgeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer minAge;
    private Integer maxAge;

    public AgeGroup(AgeGroupDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.minAge = dto.getMinAge();
        this.maxAge = dto.getMaxAge();
    }
}
