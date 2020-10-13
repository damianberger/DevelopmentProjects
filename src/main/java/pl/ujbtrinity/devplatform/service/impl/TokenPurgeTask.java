package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.repository.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;

@Service
@Transactional
public class TokenPurgeTask {

    private final VerificationTokenRepository verificationTokenRepository;

    public TokenPurgeTask(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Scheduled(cron = "* * 11 * * *")
    public void purgeExpired() {
        Date now = Date.from(Instant.now());

        verificationTokenRepository.deleteAllExpiredSince(now);
    }
}
