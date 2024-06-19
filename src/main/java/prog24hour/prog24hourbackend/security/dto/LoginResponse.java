package prog24hour.prog24hourbackend.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LoginResponse {

  private String email;
  private String token;
  private List<String> roles;

  public LoginResponse(String email, String token, List<String> roles) {
    this.email = email;
    this.token = token;
    this.roles = roles;
  }
}