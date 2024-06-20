package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "disciplines")
    private Set<Participant> participants;
}
