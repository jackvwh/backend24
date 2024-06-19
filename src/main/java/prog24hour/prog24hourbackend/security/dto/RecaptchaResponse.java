package prog24hour.prog24hourbackend.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecaptchaResponse {
    private boolean success;
    private String challenge_ts;
    private String hostname;
    private List<String> errorCodes;
}