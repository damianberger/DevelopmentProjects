package pl.ujbtrinity.devplatform.service;


import pl.ujbtrinity.devplatform.entity.User;

public interface UserService {
    User findByUsername (String username);
    void saveUser (User user);
}
