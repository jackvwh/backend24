package prog24hour.prog24hourbackend.security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import saxxen.security.dto.RecaptchaResponse;

@Service
public class RecaptchaService {

    @Value("${recaptcha.secret-key}")
    private String secretKey;

    private final WebClient webClient;

    public RecaptchaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.google.com").build();
    }

    public boolean verifyRecaptcha(String token) {
        String url = "/recaptcha/api/siteverify";

        try {
            RecaptchaResponse recaptchaResponse = this.webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path(url)
                            .queryParam("secret", secretKey)
                            .queryParam("response", token)
                            .build())
                    .retrieve()
                    .bodyToMono(RecaptchaResponse.class)
                    .block();

            return recaptchaResponse != null && recaptchaResponse.isSuccess();
        } catch (WebClientResponseException ex) {
            // Log error
            ex.printStackTrace();
            return false;
        }
    }
}


