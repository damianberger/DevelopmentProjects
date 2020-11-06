package pl.ujbtrinity.devplatform.service;


import org.springframework.web.multipart.MultipartFile;
import pl.ujbtrinity.devplatform.dto.userDto.*;
import pl.ujbtrinity.devplatform.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User findByUsername (String username);
    PrivateViewUserProfileDto getPrivateUserProfile(String username) throws IOException;
    PublicViewUserProfileDto getPublicUserProfile(String username) throws IOException;
    byte[] getUserProfilePhoto(Long id) throws IOException;
    void register (UserRegistrationDto userRegistrationDto);
    void setStatus (String userName);
    void editUserEmail (UserEmailChangeDto userProfileEditDto, String username);
    void editUserPassword (PasswordChangeDto passwordChangeDto, String username);
    void editUserFrameworks (UserFrameworkDto userFrameworkDto, String username);
    void editUserTechnologies (UserTechnologyDto userTechnologyDto, String username);
    void editUserPersonals (UserProfileChangeDto userProfileChangeDto, String username);
    List<User> findAll();
    User readUser(long id);
    boolean existsByUsername(String username);
    void createSuperAdmin(User user);
    void savePhoto(MultipartFile file, String username) throws IOException;
    boolean friendListCheck(String username, String principalName);
}
