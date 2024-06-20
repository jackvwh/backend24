package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
public class Disciplin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    private String description;

    @OneToMany (mappedBy = "disciplin" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Participant> participants;
}
