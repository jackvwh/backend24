package prog24hour.prog24hourbackend.security.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Role {

    @Id
    private String roleName;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    Set<User> users;

    public void addUser(User user) {
        if(users == null) users = new HashSet<>();
        users.add(user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roleName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Role other = (Role) obj;
        return Objects.equals(roleName, other.roleName);
    }
}
