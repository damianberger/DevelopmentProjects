package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.entity.ResetPasswordToken;

public interface ResetPasswordTokenService {
    ResetPasswordToken findByToken(String token);
    void saveToken(ResetPasswordToken resetPasswordToken);
}
