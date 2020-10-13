package pl.ujbtrinity.devplatform.dto;

import lombok.Data;

@Data
public class PasswordChangeDto {

    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirm;

}
