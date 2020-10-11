package pl.ujbtrinity.devplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ujbtrinity.devplatform.dto.PasswordChangeDto;
import pl.ujbtrinity.devplatform.dto.UserProfileDto;
import pl.ujbtrinity.devplatform.dto.UserProfileEditDto;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.security.Principal;
import java.time.LocalDate;

@RestController
public class UserProfileController {

    private final UserServiceImpl userService;

    public UserProfileController(UserServiceImpl userService) {
        this.userService = userService;
    }

    private static final String USER_PROFILE_ENDPOINT = "/user/profile";
    private static final String USER_PROFILE_EDIT_ENDPOINT = "/user/profile/personals/edit";
    private static final String USER_PASSWORD_EDIT_ENDPOINT = "/user/profile/password/edit";


    @GetMapping(USER_PROFILE_ENDPOINT)
    public ResponseEntity<UserProfileDto> readProfile(Principal principal) {
        UserProfileDto userProfile = userService.getUserProfile(principal.getName());
        if (userProfile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @PostMapping(USER_PROFILE_EDIT_ENDPOINT)
    public String editPersonals(Principal principal, @RequestBody UserProfileEditDto userProfileEditDto) {
        userService.editUserPersonals(userProfileEditDto, principal.getName());
        return "User data changed";
    }

    @PostMapping(USER_PASSWORD_EDIT_ENDPOINT)
    public String editPassword(Principal principal, @RequestBody PasswordChangeDto passwordChangeDto) {
        userService.editUserPassword(passwordChangeDto,principal.getName());
        return "Password changed";
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
