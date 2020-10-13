package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.entity.ChangeMailToken;

public interface ChangeMailTokenService {
    ChangeMailToken findByToken(String token);
    void saveToken(ChangeMailToken changeMailToken);
}
