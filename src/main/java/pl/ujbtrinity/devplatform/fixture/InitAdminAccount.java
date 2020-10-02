package pl.ujbtrinity.devplatform.fixture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.model.Status;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.time.LocalDate;

@Service
public class InitAdminAccount {

    @Value("${sa.pw.setup}")
    private String password;
    @Value("${sa.email.setup}")
    private String email;

    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public InitAdminAccount(UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void init(){
        User user = new User();
        user.setUsername("SuperAdmin");
        user.setFirstName("SuperAdmin");
        user.setLastName("SuperAdmin");
        user.setEmail(email);
        user.setCreated(LocalDate.now());
        user.setUpdated(LocalDate.now());
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(Status.ACTIVE);
        boolean existingAdmin = userService.existsByEmail(email);
        if(!existingAdmin){
            userService.saveUser(user);
        }
    }
}
