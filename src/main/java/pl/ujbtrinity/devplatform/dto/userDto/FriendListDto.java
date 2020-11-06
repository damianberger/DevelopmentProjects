package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendListDto {

    private String username;

    private LocalDateTime since;
}
