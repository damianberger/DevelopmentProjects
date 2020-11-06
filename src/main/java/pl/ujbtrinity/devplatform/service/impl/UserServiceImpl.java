package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.ujbtrinity.devplatform.dto.userDto.*;
import pl.ujbtrinity.devplatform.entity.*;
import pl.ujbtrinity.devplatform.model.Status;
import pl.ujbtrinity.devplatform.repository.*;
import pl.ujbtrinity.devplatform.service.UserService;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Value("${images.path}")
    private String finalPath;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final FrameworkRepository frameworkRepository;
    private final TechnologyRepository technologyRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final FriendListRepository friendListRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, FrameworkRepository frameworkRepository, TechnologyRepository technologyRepository, BCryptPasswordEncoder passwordEncoder, FriendListRepository friendListRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.frameworkRepository = frameworkRepository;
        this.technologyRepository = technologyRepository;
        this.passwordEncoder = passwordEncoder;
        this.friendListRepository = friendListRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean friendListCheck(String username, String principalName) {
        Set<Long> userFriends = friendListRepository.friendsCheck(userRepository.findByUsername(username).getId());
        Long principalId = userRepository.findByUsername(principalName).getId();
        return userFriends.contains(principalId) || username.equals(principalName);
    }

    @Override
    public PrivateViewUserProfileDto getPrivateUserProfile(String username) {
        return PrivateViewUserProfileDto.fromUser(findByUsername(username));
    }

    @Override
    public PublicViewUserProfileDto getPublicUserProfile(String username) {
        return PublicViewUserProfileDto.fromUser(findByUsername(username));
    }

    @Override
    public byte[] getUserProfilePhoto(Long id) throws IOException {
        Optional<User> user = userRepository.findById(id);
        Path path = Paths.get(finalPath + id + ".png");
        return Files.readAllBytes(path);
    }

    @Override
    public void register(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto.getPassword().matches(userRegistrationDto.getPasswordConfirmation())) {
            User user = userRegistrationDto.toUser();
            user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            user.setCreated(LocalDate.now());
            user.setUpdated(LocalDate.now());
            user.setStatus(Status.NOT_ACTIVE);
            Role userRole;
            if (userRegistrationDto.getRole().equals("Developer")) {
                userRole = roleRepository.findByName("ROLE_DEVELOPER");
            } else {
                userRole = roleRepository.findByName("ROLE_CUSTOMER");
            }
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepository.save(user);
        }
    }

    @Override
    public void setStatus(String userName) {
        User user = userRepository.findByUsername(userName);
        user.setUpdated(LocalDate.now());
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void createSuperAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        Role userRole = roleRepository.findByName("ROLE_SUPER_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void savePhoto(MultipartFile file, String username) throws IOException {
        if (file.getContentType() == "image/png") {
            User user = userRepository.findByUsername(username);
            Path path = Paths.get(finalPath + user.getId() + "." + file.getContentType().split("/")[1]);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            userRepository.save(user);
        }
    }

    @Override
    public void editUserEmail(UserEmailChangeDto userEmailChangeDto, String username) {
        User user = userRepository.findByUsername(username);
        if (passwordEncoder.matches(userEmailChangeDto.getCurrentPassword(), user.getPassword())) {
            user.setEmail(userEmailChangeDto.getEmail());
            user.setUpdated(LocalDate.now());
            userRepository.save(user);
        }
    }

    @Override
    public void editUserPassword(PasswordChangeDto passwordChangeDto, String username) {
        User user = userRepository.findByUsername(username);
        if (passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), user.getPassword())) {
            if (passwordChangeDto.getNewPassword().matches(passwordChangeDto.getNewPasswordConfirm())) {
                user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
                user.setUpdated(LocalDate.now());
                userRepository.save(user);
            }
        }
    }

    @Override
    public void editUserFrameworks(UserFrameworkDto userFrameworkDto, String username) {
        User user = userRepository.findByUsername(username);
        Set<Framework> userFrameworks = new HashSet<>();
        for (String framework : userFrameworkDto.getFrameworks()) {
            userFrameworks.add(frameworkRepository.findByName(framework));
        }
        user.setFrameworks(userFrameworks);
        userRepository.save(user);
    }

    @Override
    public void editUserTechnologies(UserTechnologyDto userTechnologyDto, String username) {
        User user = userRepository.findByUsername(username);
        Set<Technology> userTechnologies = new HashSet<>();
        for (String technology : userTechnologyDto.getTechnologies()) {
            userTechnologies.add(technologyRepository.findByName(technology));
        }
        user.setTechnologies(userTechnologies);
        userRepository.save(user);
    }

    @Override
    public void editUserPersonals(UserProfileChangeDto userProfileChangeDto, String username) {
        User user = userRepository.findByUsername(username);
        user.setFirstName(userProfileChangeDto.getFirstName());
        user.setLastName(userProfileChangeDto.getLastName());
        user.setUsername(userProfileChangeDto.getUsername());
        user.setDescription(userProfileChangeDto.getDescription());
        user.setCity(userProfileChangeDto.getCity());
        userRepository.save(user);
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User readUser(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public Set<String> userFriendList(String username) {
        Set<Long> friendsId = friendListRepository.friendsCheck(userRepository.findByUsername(username).getId());
        Set<String> friends = new HashSet<>();
        for (Long friendId: friendsId) {
            friends.add(userRepository.getOne(friendId).getUsername());
        }
        return friends;
    }

    @Override
    public String removeFriend(String username, String principalName) {
        return null;
    }

    @Override
    public Set<FriendListReceived> receivedFriendInvitations(String username) {
        return null;
    }

    @Override
    public Set<FriendListRequested> requestedFriendInvitations(String username) {
        return null;
    }

    @Override
    public String acceptFriendRequest(String username, Long id) {
        return null;
    }

    @Override
    public String declineFriendRequest(String username, Long id) {
        return null;
    }
}
