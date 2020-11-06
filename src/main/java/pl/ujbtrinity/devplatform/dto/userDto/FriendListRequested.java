package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FriendListRequested {

    private String invitedUser;

    private LocalDateTime invited;
}
