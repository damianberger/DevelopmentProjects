package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.ChangeMailToken;
import pl.ujbtrinity.devplatform.repository.ChangeMailTokenRepository;
import pl.ujbtrinity.devplatform.service.ChangeMailTokenService;

@Service
public class ChangeMailTokenServiceImpl implements ChangeMailTokenService {

    private final ChangeMailTokenRepository changeMailTokenRepository;

    public ChangeMailTokenServiceImpl(ChangeMailTokenRepository changeMailTokenRepository) {
        this.changeMailTokenRepository = changeMailTokenRepository;
    }

    @Override
    public ChangeMailToken findByToken(String token) {
        return changeMailTokenRepository.findByToken(token);
    }

    @Override
    public void saveToken(ChangeMailToken changeMailToken) {
        changeMailTokenRepository.save(changeMailToken);
    }
}
