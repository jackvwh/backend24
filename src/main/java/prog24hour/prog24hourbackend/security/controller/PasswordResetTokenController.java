package prog24hour.prog24hourbackend.security.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import saxxen.security.entity.User;
import saxxen.security.service.PasswordResetTokenService;
import java.util.Optional;

@RestController
@RequestMapping("/password")
public class PasswordResetTokenController {

    private final PasswordResetTokenService passwordResetService;

    public PasswordResetTokenController(PasswordResetTokenService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    // Create a new password reset token
    @PostMapping("/token")
    public ResponseEntity<String> resetPassword(@RequestParam @Valid @Email String email) {
         passwordResetService.createPasswordResetToken(email);
        return ResponseEntity.ok("Password reset token created successfully " );
    }

    @GetMapping("/change")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        String result = passwordResetService.validateToken(token);
        if (result != null) {
            return ResponseEntity.ok("Token is valid " + result);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found");
        }
    }

    @PostMapping("/savepassword")
    public ResponseEntity<String> savePassword(@RequestParam String email, String newPassword) {
            passwordResetService.savePassword(email, newPassword);
            return ResponseEntity.ok("Password updated successfully");
    }
}
