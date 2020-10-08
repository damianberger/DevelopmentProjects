package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.dto.UserRegistrationDto;
import pl.ujbtrinity.devplatform.entity.Role;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.model.Status;
import pl.ujbtrinity.devplatform.repository.RoleRepository;
import pl.ujbtrinity.devplatform.repository.UserRepository;
import pl.ujbtrinity.devplatform.service.UserService;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void registerCustomer(UserRegistrationDto userRegistrationDto) {
        User user = userRegistrationDto.toUser();
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setCreated(LocalDate.now());
        user.setUpdated(LocalDate.now());
        user.setStatus(Status.ACTIVE);
        Role userRole = roleRepository.findByName("ROLE_CUSTOMER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void registerDeveloper(UserRegistrationDto userRegistrationDto) {
        User user = userRegistrationDto.toUser();
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setCreated(LocalDate.now());
        user.setUpdated(LocalDate.now());
        user.setStatus(Status.ACTIVE);
        Role userRole = roleRepository.findByName("ROLE_DEVELOPER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
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
    public void editUser(User user) {
        User userFromDB = userRepository.getOne(user.getId());
        userFromDB.setUsername(user.getUsername());
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setPassword(passwordEncoder.encode(user.getPassword()));
        userFromDB.setCreated(user.getCreated());
        userFromDB.setUpdated(LocalDate.now());
        userFromDB.setStatus(user.getStatus());
        userFromDB.setRoles(user.getRoles());
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
    public boolean existsByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }
}
