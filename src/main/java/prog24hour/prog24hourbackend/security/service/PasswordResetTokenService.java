package prog24hour.prog24hourbackend.security.service;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saxxen.dtubar.service.EmailService;
import saxxen.security.entity.PasswordResetToken;
import saxxen.security.entity.User;
import saxxen.security.repository.PasswordResetTokenRepository;
import saxxen.security.repository.UserRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository , UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    //Method to create a new password reset token
    @Transactional
    public void createPasswordResetToken(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken myToken = new PasswordResetToken();

        passwordResetTokenRepository.save(myToken);

        myToken.setToken(token);

        myToken.setUser(user);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 3);
        Date expiryDate = calendar.getTime();

        myToken.setExpiryDate(expiryDate);

        user.setPasswordResetToken(myToken);

        passwordResetTokenRepository.save(myToken);
        userRepository.save(user);

        // send email with email service to user
        try {
            emailService.sendEmail(email, EmailService.EmailType.RESET_PASSWORD, user.getResident().getFirstName(), token);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to validate token
    public String validateToken(String token) {
        final PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
        log.info("Token found: " + passToken);
        return !isTokenFound(passToken) ? "Token is not found"
                : isTokenExpired(passToken) ? "Token is expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryDate().before(new Date());
    }

    //Method to save password
    @Transactional
    public void savePassword(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
