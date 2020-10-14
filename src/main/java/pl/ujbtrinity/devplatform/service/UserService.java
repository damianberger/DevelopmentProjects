package pl.ujbtrinity.devplatform.service;


import pl.ujbtrinity.devplatform.dto.userDto.*;
import pl.ujbtrinity.devplatform.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername (String username);
    UserProfileDto getUserProfile(String username);
    void register (UserRegistrationDto userRegistrationDto);
    void setStatus (String userName);
    void editUserEmail (UserEmailChangeDto userProfileEditDto, String username);
    void editUserPassword (PasswordChangeDto passwordChangeDto, String username);
    void editUserFrameworks (UserFrameworkDto userFrameworkDto, String username);
    void editUserTechnologies (UserTechnologyDto userTechnologyDto, String username);
    List<User> findAll();
    User readUser(long id);
    boolean existsByUsername(String username);
    void createSuperAdmin(User user);
}
