package pl.ujbtrinity.devplatform.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ujbtrinity.devplatform.dto.UserRegistrationDto;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

@RestController
public class RegistrationController {

    private UserServiceImpl userService;

    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/customer/registration")
    public String customerRegistration(@RequestBody UserRegistrationDto userRegistrationDto) {
        this.userService.registerCustomer(userRegistrationDto);
        return "Ok";
    }

    @PostMapping("/developer/registration")
    public String developerRegistration(@RequestBody UserRegistrationDto userRegistrationDto) {
        this.userService.registerDeveloper(userRegistrationDto);
        return "Ok";
    }
}
