package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import prog24hour.prog24hourbackend.security.entity.User;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Participant extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "disciplin_id")
    private Disciplin disciplin;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private Result result;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "age_group_id", referencedColumnName = "id")
    private AgeGroup ageGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;
}


