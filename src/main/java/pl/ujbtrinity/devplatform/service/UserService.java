package pl.ujbtrinity.devplatform.service;


import pl.ujbtrinity.devplatform.dto.PasswordChangeDto;
import pl.ujbtrinity.devplatform.dto.UserProfileDto;
import pl.ujbtrinity.devplatform.dto.UserEmailChangeDto;
import pl.ujbtrinity.devplatform.dto.UserRegistrationDto;
import pl.ujbtrinity.devplatform.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername (String username);
    UserProfileDto getUserProfile(String username);
    void registerCustomer (UserRegistrationDto userRegistrationDto);
    void registerDeveloper (UserRegistrationDto userRegistrationDto);
    void editUserEmail (UserEmailChangeDto userProfileEditDto, String username);
    void editUserPassword (PasswordChangeDto passwordChangeDto, String username);
    List<User> findAll();
    User readUser(long id);
    boolean existsByUsername(String username);
    void createSuperAdmin(User user);
}
