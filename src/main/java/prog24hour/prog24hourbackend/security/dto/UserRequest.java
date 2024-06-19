package prog24hour.prog24hourbackend.security.dto;

import saxxen.dtubar.dto.ResidentDto;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    @NonNull
    ResidentDto resident;
    @NonNull
    String password;
}
