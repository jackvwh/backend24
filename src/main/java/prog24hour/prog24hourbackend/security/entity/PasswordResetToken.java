package prog24hour.prog24hourbackend.security.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class PasswordResetToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    @OneToOne
    @JoinColumn(name = "reset_password_token_id")
    private User user;

    private Date expiryDate;
}
