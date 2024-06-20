package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ResultType {
    @Id
    private Integer id;
    private String unit;
}
