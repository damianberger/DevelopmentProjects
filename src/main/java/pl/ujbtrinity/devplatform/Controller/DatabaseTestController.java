package pl.ujbtrinity.devplatform.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Role;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.service.impl.FrameworkServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.RoleServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.TechnologyServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class DatabaseTestController {

    private final TechnologyServiceImpl technologyService;
    private final FrameworkServiceImpl frameworkService;
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public DatabaseTestController(TechnologyServiceImpl technologyService, FrameworkServiceImpl frameworkService, UserServiceImpl userService, RoleServiceImpl roleService) {
        this.technologyService = technologyService;
        this.frameworkService = frameworkService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("tech")
    List<Technology> getT(){
        return technologyService.FindAll();
    }

    @GetMapping("frame")
    List<Framework> getF(){
        return frameworkService.FindAll();
    }

    @GetMapping("user")
    List<User> getU(){
        return userService.FindAll();
    }

    @GetMapping("/role")
    List<Role> getR(){
        return roleService.findAll();
    }
}
