package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date resultDate;
    private Integer resultValue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_type_id", referencedColumnName = "id")
    private Discipline discipline;
}
