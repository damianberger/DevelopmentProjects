package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;


@Data
public class UserEmailChangeDto {

    @Email
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    private String currentPassword;
}
