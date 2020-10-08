package pl.ujbtrinity.devplatform.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ujbtrinity.devplatform.dto.UserDto;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HomePageController {

    private UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String HomePage(){
        return "Test";
    }

}
