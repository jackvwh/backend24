package prog24hour.prog24hourbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.entity.Participant;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipantDto implements Serializable {

    private Integer id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Birth date is required")
    private String birthDate;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp="(^$|[0-9]{8})",message = "Mobile number must be 8 digits")
    private String phone;
    private AgeGroupDto ageGroup;
    private String gender;

    @Valid
    private ClubDto club;

    @Valid
    private Set<DisciplineDto> disciplines;

    public ParticipantDto(Participant participant) {
        this.id = participant.getId();
        this.firstName = participant.getFirstName();
        this.lastName = participant.getLastName();
        this.birthDate = participant.getBirthDate();
        this.email = participant.getEmail();
        this.phone = participant.getPhone();
    }
}
