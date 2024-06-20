package prog24hour.prog24hourbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.entity.Address;
import prog24hour.prog24hourbackend.entity.Club;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClubDto implements Serializable {

    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    @Valid
    private AddressDto address;

    public ClubDto(Club club) {
        this.id = club.getId();
        this.name = club.getName();
        this.address = new AddressDto(club.getAddress());
    }
}
