package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.repository.ChangeMailTokenRepository;
import pl.ujbtrinity.devplatform.repository.ChangePasswordTokenRepository;
import pl.ujbtrinity.devplatform.repository.ResetPasswordTokenRepository;
import pl.ujbtrinity.devplatform.repository.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;

@Service
@Transactional
public class TokenPurgeTask {

    private final VerificationTokenRepository verificationTokenRepository;
    private final ChangePasswordTokenRepository changePasswordTokenRepository;
    private final ChangeMailTokenRepository changeMailTokenRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    public TokenPurgeTask(VerificationTokenRepository verificationTokenRepository, ChangePasswordTokenRepository changePasswordTokenRepository, ChangeMailTokenRepository changeMailTokenRepository, ResetPasswordTokenRepository resetPasswordTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.changePasswordTokenRepository = changePasswordTokenRepository;
        this.changeMailTokenRepository = changeMailTokenRepository;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
    }

    @Scheduled(cron = "* * 11 * * *")
    public void purgeExpired() {
        Date now = Date.from(Instant.now());

        verificationTokenRepository.deleteAllExpiredSince(now);
        changePasswordTokenRepository.deleteAllExpiredSince(now);
        changeMailTokenRepository.deleteAllExpiredSince(now);
        resetPasswordTokenRepository.deleteAllExpiredSince(now);
    }
}
