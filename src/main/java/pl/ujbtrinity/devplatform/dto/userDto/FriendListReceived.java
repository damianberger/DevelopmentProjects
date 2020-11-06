package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FriendListReceived {

    private String invitingUser;

    private LocalDateTime invited;
}
