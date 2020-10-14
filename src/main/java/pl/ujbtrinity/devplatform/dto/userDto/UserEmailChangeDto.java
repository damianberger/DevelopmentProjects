package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;


@Data
public class UserEmailChangeDto {
    private String email;
    private String currentPassword;
}
