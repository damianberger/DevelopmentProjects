package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.VerificationToken;
import pl.ujbtrinity.devplatform.repository.VerificationTokenRepository;
import pl.ujbtrinity.devplatform.service.VerificationTokenService;
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }
}
