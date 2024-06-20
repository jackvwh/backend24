package prog24hour.prog24hourbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.entity.Address;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto implements Serializable {

    private Integer id;

    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank
    private Integer streetNumber;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "Zip code is required")
    private String zipCode;
    @NotBlank(message = "Country is required")
    private String country;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.streetNumber = address.getStreetNumber();
        this.city = address.getCity();
        this.zipCode = address.getZipCode();
        this.country = address.getCountry();
    }

}
