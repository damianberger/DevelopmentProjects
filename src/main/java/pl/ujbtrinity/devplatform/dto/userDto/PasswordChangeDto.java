package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class PasswordChangeDto {

    private String currentPassword;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}")
    private String newPassword;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}")
    private String newPasswordConfirm;

}
