package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.entity.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);
    void saveToken(VerificationToken verificationToken);
}
