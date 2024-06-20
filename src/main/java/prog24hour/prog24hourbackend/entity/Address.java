package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.dto.AddressDto;

@Entity
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;
    private Integer streetNumber;
    private String city;
    private String zipCode;
    private String country;

    public Address(AddressDto dto) {
        this.id = dto.getId();
        this.street = dto.getStreet();
        this.streetNumber = dto.getStreetNumber();
        this.city = dto.getCity();
        this.zipCode = dto.getZipCode();
        this.country = dto.getCountry();
    }

}
