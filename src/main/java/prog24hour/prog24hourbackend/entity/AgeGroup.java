package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class AgeGroup {

    @Id
    private Integer id;

    private Integer minAge;
    private Integer maxAge;
}
