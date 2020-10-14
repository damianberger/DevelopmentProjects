package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.entity.ChangePasswordToken;

public interface ChangePasswordTokenService {
    ChangePasswordToken findByToken(String token);
    void saveToken(ChangePasswordToken changePasswordToken);
}
