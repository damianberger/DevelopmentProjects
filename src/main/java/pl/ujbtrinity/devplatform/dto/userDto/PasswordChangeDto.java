package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;

@Data
public class PasswordChangeDto {

    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirm;

}
