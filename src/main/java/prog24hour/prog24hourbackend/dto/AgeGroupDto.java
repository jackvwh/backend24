package prog24hour.prog24hourbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.entity.AgeGroup;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgeGroupDto implements Serializable {

    private Integer id;
    private String name;
    private Integer minAge;
    private Integer maxAge;

    public AgeGroupDto(AgeGroup ageGroup) {
        this.id = ageGroup.getId();
        this.name = ageGroup.getName();
        this.minAge = ageGroup.getMinAge();
        this.maxAge = ageGroup.getMaxAge();
    }
}
