package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;
import pl.ujbtrinity.devplatform.model.FriendStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class FriendListReceived {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long invitingUser;

    private LocalDateTime invited;
}
