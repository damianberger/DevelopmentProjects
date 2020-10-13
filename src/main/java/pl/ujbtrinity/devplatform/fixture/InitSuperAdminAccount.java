package pl.ujbtrinity.devplatform.fixture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.time.LocalDate;

@Service
public class InitSuperAdminAccount {

    @Value("${sa.pw.setup}")
    private String password;
    @Value("${sa.email.setup}")
    private String email;

    private final UserServiceImpl userService;

    public InitSuperAdminAccount(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void initSuperAdmin(){
        User user = new User();
        user.setUsername("SuperAdmin");
        user.setFirstName("SuperAdmin");
        user.setLastName("SuperAdmin");
        user.setEmail(email);
        user.setCreated(LocalDate.now());
        user.setUpdated(LocalDate.now());
        user.setPassword(password);
        boolean existingAdmin = userService.existsByUsername("SuperAdmin");
        if(!existingAdmin){
            userService.createSuperAdmin(user);
        }
    }
}
