package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Result {

    @Id
    private Integer id;

    private Date resultDate;
    private Integer resultValue;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Participant> participants;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Disciplin disciplin;
}
