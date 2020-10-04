package pl.ujbtrinity.devplatform.fixture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.Role;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.model.Status;
import pl.ujbtrinity.devplatform.repository.RoleRepository;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class InitAdminAccount {

    @Value("${sa.pw.setup}")
    private String password;
    @Value("${sa.email.setup}")
    private String email;

    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public InitAdminAccount(UserServiceImpl userService, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void initAdmin(){
        User user = new User();
        user.setUsername("SuperAdmin");
        user.setFirstName("SuperAdmin");
        user.setLastName("SuperAdmin");
        user.setEmail(email);
        user.setCreated(LocalDate.now());
        user.setUpdated(LocalDate.now());
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(Status.ACTIVE);

//        TO::DO - not working properly
        Role userRole = roleRepository.findByName("ROLE_SUPERADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));

        boolean existingAdmin = userService.existsByEmail(email);
        if(!existingAdmin){
            userService.saveUser(user);
        }
    }
}
