package prog24hour.prog24hourbackend.security.entity;

import saxxen.dtubar.entity.BaseEntity;
import saxxen.dtubar.entity.Resident;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

    //60 = length of a bcrypt encoded password
    @Column(nullable = false, length = 60)
    @NotBlank(message = "Password is required")
    @JsonIgnore
    private String password;

    @OneToOne
    @JoinColumn(name = "resident_id", referencedColumnName = "id")
    private Resident resident;

    @OneToOne(mappedBy = "user")
    private PasswordResetToken passwordResetToken;

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
        return this.getResident().getEmail();
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
