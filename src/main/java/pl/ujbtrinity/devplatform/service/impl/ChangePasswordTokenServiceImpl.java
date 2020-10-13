package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.ChangePasswordToken;
import pl.ujbtrinity.devplatform.repository.ChangePasswordTokenRepository;
import pl.ujbtrinity.devplatform.service.ChangePasswordTokenService;

@Service
public class ChangePasswordTokenServiceImpl implements ChangePasswordTokenService {

    private final ChangePasswordTokenRepository changePasswordTokenRepository;

    public ChangePasswordTokenServiceImpl(ChangePasswordTokenRepository changePasswordTokenRepository) {
        this.changePasswordTokenRepository = changePasswordTokenRepository;
    }


    @Override
    public ChangePasswordToken findByToken(String token) {
        return changePasswordTokenRepository.findByToken(token);
    }

    @Override
    public void saveToken(ChangePasswordToken changePasswordToken) {
        changePasswordTokenRepository.save(changePasswordToken);
    }
}
