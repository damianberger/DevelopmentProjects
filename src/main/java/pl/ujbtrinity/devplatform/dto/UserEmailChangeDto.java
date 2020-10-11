package pl.ujbtrinity.devplatform.dto;

import lombok.Data;


@Data
public class UserEmailChangeDto {
    private String email;
    private String currentPassword;
}
