package prog24hour.prog24hourbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.entity.Result;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDto implements Serializable {

    private Integer id;

    private Date resultDate;
    private Integer resultValue;

    @Valid
    private ParticipantDto participant;

    @Valid
    private DisciplineDto discipline;

    public ResultDto(Result result) {
        this.id = result.getId();
        this.resultDate = result.getResultDate();
        this.resultValue = result.getResultValue();
        this.participant = new ParticipantDto(result.getParticipant());
        this.discipline = new DisciplineDto(result.getDiscipline());
    }
}
