package prog24hour.prog24hourbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DisciplineDto implements Serializable {

        private Integer id;

        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Description is required")
        private String description;

        private Set<ParticipantDto> participants;
}
