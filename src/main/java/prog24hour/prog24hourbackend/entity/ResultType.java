package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.dto.ResultTypeDto;

@Data
@Entity
@NoArgsConstructor
public class ResultType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public ResultType(ResultTypeDto dto) {
        this.name = dto.getName();
    }
}
