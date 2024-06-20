package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    private Integer id;
    private String street;
    private String streetNumber;
    private String city;
    private String zipCode;
    private String country;
}
