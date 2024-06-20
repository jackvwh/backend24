package prog24hour.prog24hourbackend.security.dto;

import lombok.Data;
import lombok.NonNull;
import prog24hour.prog24hourbackend.entity.GenderType;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserRequest implements Serializable {
    @NonNull
   String firstName;
    @NonNull
    String lastName;
    @NonNull
    String email;
    @NonNull
    String password;
    @NonNull
    LocalDate dob;
    @NonNull
    String phone;
    @NonNull
    GenderType gender;
}
