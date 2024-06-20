package prog24hour.prog24hourbackend.security.dto;

import lombok.Data;
import lombok.NonNull;
import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
