package prog24hour.prog24hourbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import prog24hour.prog24hourbackend.dto.DisciplineDto;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "disciplines")
    private Set<Participant> participants;

    public Discipline(DisciplineDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
    }
}
