package prog24hour.prog24hourbackend.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saxxen.security.service.RecaptchaService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/recaptcha/")
public class RecaptchaController {

    private final RecaptchaService recaptchaService;

    public RecaptchaController(RecaptchaService recaptchaService) {
        this.recaptchaService = recaptchaService;
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyRecaptcha(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        Boolean response = recaptchaService.verifyRecaptcha(token);
        return ResponseEntity.ok(response);
    }
}