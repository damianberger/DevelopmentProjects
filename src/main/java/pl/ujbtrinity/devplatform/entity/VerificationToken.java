package pl.ujbtrinity.devplatform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "verification_tokens")
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;
    private String token;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }

    public VerificationToken(User user) {
        this.user = user;
        expiryDate = calculateExpiryDate(EXPIRATION);
        token = UUID.randomUUID().toString();
    }
}
