package pl.ujbtrinity.devplatform.service;


import pl.ujbtrinity.devplatform.dto.UserDto;
import pl.ujbtrinity.devplatform.dto.UserRegistrationDto;
import pl.ujbtrinity.devplatform.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername (String username);
    void registerCustomer (UserRegistrationDto userRegistrationDto);
    void registerDeveloper (UserRegistrationDto userRegistrationDto);
    void editUser (User user);
    List<User> findAll();
    User readUser(long id);
    boolean existsByEmail(String email);
    void createSuperAdmin(User user);
    UserDto convertToDto(User user);
}
