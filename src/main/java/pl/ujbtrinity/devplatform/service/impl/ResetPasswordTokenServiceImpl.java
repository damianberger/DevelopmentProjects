package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.ResetPasswordToken;
import pl.ujbtrinity.devplatform.entity.VerificationToken;
import pl.ujbtrinity.devplatform.repository.ResetPasswordTokenRepository;
import pl.ujbtrinity.devplatform.service.ResetPasswordTokenService;

@Service
public class ResetPasswordTokenServiceImpl implements ResetPasswordTokenService {

    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    public ResetPasswordTokenServiceImpl(ResetPasswordTokenRepository resetPasswordTokenRepository) {
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
    }

    @Override
    public ResetPasswordToken findByToken(String token) {
        return resetPasswordTokenRepository.findByToken(token);
    }

    @Override
    public void saveToken(ResetPasswordToken resetPasswordToken) {
        resetPasswordTokenRepository.save(resetPasswordToken);
    }
}
