package prog24hour.prog24hourbackend.security.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest{
   String email;
   String password;
}
