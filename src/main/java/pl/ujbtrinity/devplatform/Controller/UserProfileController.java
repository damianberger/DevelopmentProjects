package pl.ujbtrinity.devplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ujbtrinity.devplatform.dto.UserProfileDto;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.security.Principal;

@RestController
public class UserProfileController {

    private final UserServiceImpl userService;

    public UserProfileController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Inject services
     */

    /**
     * endpoints that should be here
     */

    @GetMapping("/user/profile/")
    public ResponseEntity<UserProfileDto> readProfile(Principal principal) {
        UserProfileDto userCredentials = userService.getUserCredentials(principal.getName());
        if(userCredentials == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userCredentials, HttpStatus.OK);
    }

    @PostMapping("user/personals/")
    public String addPersonals(Principal principal) {

        return "user personals/next step";
    }


    @PostMapping("user/technologies/{id}")
    public String addTechnologiesUsedByCurrentUser(@PathVariable String id) {
        // complementing the technologies used
        return "user technologies/next step";
    }

    @PostMapping("user/frameworks/{id}")
    public String addFrameworksUsedByCurrentUser(@PathVariable String id) {
        // complementing the frameworks used
        return "user frameworks/next step";
    }

    @PostMapping("user/photo/{id}")
    public String adPhotography(@PathVariable String id) {
        // adding a user's photo
        return "user photo/next step";
    }

    /**
     * This is an option to think about
     *
     creating a new entity containing additional data about the user -
     for example, an address, something about yourself; or add the appropriate fields to User entity
     */

    /**
     * endpoints for editing data
     */


}
