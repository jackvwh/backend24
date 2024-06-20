package prog24hour.prog24hourbackend.security.dto;

import lombok.Data;
import prog24hour.prog24hourbackend.security.entity.Role;
import prog24hour.prog24hourbackend.security.entity.User;

import java.io.Serializable;
import java.util.List;

@Data
public class UserResponse implements Serializable {
    Integer id;
    String email;
    List<String> roleNames;

    public UserResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.roleNames = user.getRoles().stream().map(Role::getRoleName).toList();
    }
}

