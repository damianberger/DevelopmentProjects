package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class FriendListRequested {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long invitedUser;

    private LocalDateTime invited;
}
