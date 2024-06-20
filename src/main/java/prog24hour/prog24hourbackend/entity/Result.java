package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.dto.ResultDto;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
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

    public Result(ResultDto dto) {
        this.resultDate = dto.getResultDate();
        this.resultValue = dto.getResultValue();
        this.participant = new Participant(dto.getParticipant());
        this.discipline = new Discipline(dto.getDiscipline());
    }
}
