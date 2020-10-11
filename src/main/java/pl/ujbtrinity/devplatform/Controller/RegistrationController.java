package pl.ujbtrinity.devplatform.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ujbtrinity.devplatform.dto.UserRegistrationDto;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

@RestController
public class RegistrationController {

    private final UserServiceImpl userService;

    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public String registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        this.userService.register(userRegistrationDto);

        return "user registered";
    }
}
