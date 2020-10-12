package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.dto.*;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Role;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.model.Status;
import pl.ujbtrinity.devplatform.repository.FrameworkRepository;
import pl.ujbtrinity.devplatform.repository.RoleRepository;
import pl.ujbtrinity.devplatform.repository.TechnologyRepository;
import pl.ujbtrinity.devplatform.repository.UserRepository;
import pl.ujbtrinity.devplatform.service.UserService;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final FrameworkRepository frameworkRepository;
    private final TechnologyRepository technologyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, FrameworkRepository frameworkRepository, TechnologyRepository technologyRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.frameworkRepository = frameworkRepository;
        this.technologyRepository = technologyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserProfileDto getUserProfile(String username) {
        return UserProfileDto.fromUser(findByUsername(username));
    }

    @Override
    public void register(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto.getPassword().matches(userRegistrationDto.getPasswordConfirmation())) {
            User user = userRegistrationDto.toUser();
            user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            user.setCreated(LocalDate.now());
            user.setUpdated(LocalDate.now());
            user.setStatus(Status.ACTIVE);
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
    public void createSuperAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        Role userRole = roleRepository.findByName("ROLE_SUPER_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
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
        Set<String> frameworks = (frameworkRepository.findAll()
                .stream().map(Framework::getName)
                .collect(Collectors.toSet())
        );
        Set<String> userFrameworks = frameworks.stream()
                .distinct()
                .filter(userFrameworkDto.getFrameworks()::contains)
                .collect(Collectors.toSet());

        Set<Framework> frameworksToDB = new HashSet<>();
        for (String frameworkToDB : userFrameworks) {
            frameworksToDB.add(frameworkRepository.findByName(frameworkToDB));
        }
        user.setFrameworks(frameworksToDB);
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
}
