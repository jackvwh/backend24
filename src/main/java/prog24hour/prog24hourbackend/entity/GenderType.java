package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import prog24hour.prog24hourbackend.security.entity.User;

@Entity
@Data
public class GenderType {

    @Id
    private Integer id;
    private String name;
}
