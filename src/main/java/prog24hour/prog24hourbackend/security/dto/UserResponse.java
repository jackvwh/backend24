package prog24hour.prog24hourbackend.security.dto;

import saxxen.dtubar.dto.ResidentDto;
import saxxen.security.entity.Role;
import saxxen.security.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserResponse implements Serializable {
    Integer id;
    ResidentDto resident;
    List<String> roleNames;

    public UserResponse(User user){
        this.id = user.getId();
        this.resident = new ResidentDto(user.getResident());
        this.roleNames = user.getRoles().stream().map(Role::getRoleName).toList();
    }
}

