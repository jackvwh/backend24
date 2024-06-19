package prog24hour.prog24hourbackend.security.dto;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class PasswordResetTokenDto {
    @Valid
    private String newPassword;
}
