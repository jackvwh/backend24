package prog24hour.prog24hourbackend.security.entity;

import prog24hour.prog24hourbackend.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import prog24hour.prog24hourbackend.entity.GenderType;
import prog24hour.prog24hourbackend.entity.Participant;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User extends BaseEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gender_type_id", referencedColumnName = "id")
    private GenderType gender;

    //60 = length of a bcrypt encoded password
    @Column(nullable = false, length = 60)
//    @NotBlank(message = "Password is required")
    @JsonIgnore
    private String password;

    @OneToOne
    @JoinTable(name = "user_participant",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id", referencedColumnName = "id"))
    private Participant participant;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roleName", referencedColumnName = "roleName")})
    Set<Role> roles = new HashSet<>();

    public void setPassword(String pw){
        if(pw.length()<60){
            throw new IllegalArgumentException("Password is not encoded");
        }
        this.password = pw;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).toList();
    }

    public void addRole(Role roleToAdd) {
        if (!roles.contains(roleToAdd)) {
            roles.add(roleToAdd);
            roleToAdd.addUser(this);
        }
    }

    public void removeRole(Role roleToRemove) {
        roles.remove(roleToRemove);
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return Objects.equals(id, other.id);
    }
}
