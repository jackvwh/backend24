package prog24hour.prog24hourbackend.entity;

import prog24hour.prog24hourbackend.dto.ParticipantDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Participant extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "participant_disciplin",
            joinColumns = @JoinColumn(name = "participant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "disciplin_id", referencedColumnName = "id"))
    private Set<Discipline> disciplines;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "age_group_id", referencedColumnName = "id")
    private AgeGroup ageGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gender_type_id", referencedColumnName = "id")
    private GenderType gender;

    public void addDiscipline(Discipline discipline) {
        if (!disciplines.contains(discipline)) {
            disciplines.add(discipline);
        }
    }

    public void removeAllDisciplines() {
        disciplines.clear();
    }

    public Integer getAge() {
        return LocalDate.now().getYear() - LocalDate.parse(birthDate).getYear();
    }

    public Participant(ParticipantDto dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.birthDate = dto.getBirthDate();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
    }
}


