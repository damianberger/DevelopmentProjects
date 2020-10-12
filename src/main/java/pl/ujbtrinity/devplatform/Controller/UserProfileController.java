package pl.ujbtrinity.devplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ujbtrinity.devplatform.dto.PasswordChangeDto;
import pl.ujbtrinity.devplatform.dto.UserFrameworkDto;
import pl.ujbtrinity.devplatform.dto.UserProfileDto;
import pl.ujbtrinity.devplatform.dto.UserEmailChangeDto;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.security.Principal;

@RestController
public class UserProfileController {

    private final UserServiceImpl userService;

    public UserProfileController(UserServiceImpl userService) {
        this.userService = userService;
    }

    private static final String USER_PROFILE_ENDPOINT = "/user/profile";
    private static final String USER_EMAIL_CHANGE_ENDPOINT = "/user/profile/email/change";
    private static final String USER_PASSWORD_CHANGE_ENDPOINT = "/user/profile/password/change";
    private static final String USER_FRAMEWORKS_EDITION_ENDPOINT = "/user/profile/frameworks/edit";


    @GetMapping(USER_PROFILE_ENDPOINT)
    public ResponseEntity<UserProfileDto> readProfile(Principal principal) {
        UserProfileDto userProfile = userService.getUserProfile(principal.getName());
        if (userProfile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @PostMapping(USER_EMAIL_CHANGE_ENDPOINT)
    public String editUserEmail(Principal principal, @RequestBody UserEmailChangeDto userProfileEditDto) {
        userService.editUserEmail(userProfileEditDto, principal.getName());
        return "User email changed";
    }

    @PostMapping(USER_PASSWORD_CHANGE_ENDPOINT)
    public String editUserPassword(Principal principal, @RequestBody PasswordChangeDto passwordChangeDto) {
        userService.editUserPassword(passwordChangeDto,principal.getName());
        return "User password changed";
    }


    @PostMapping("user/technologies/{id}")
    public String addTechnologiesUsedByCurrentUser(@PathVariable String id) {
        // complementing the technologies used
        return "user technologies/next step";
    }

    @PostMapping(USER_FRAMEWORKS_EDITION_ENDPOINT)
    public String addFrameworksUsedByCurrentUser(Principal principal, @RequestBody UserFrameworkDto userFrameworkDto) {
        userService.editUserFrameworks(userFrameworkDto, principal.getName());
        return "User frameworks updated";
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
