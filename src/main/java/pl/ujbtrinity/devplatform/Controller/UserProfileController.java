package pl.ujbtrinity.devplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.ujbtrinity.devplatform.dto.userDto.*;
import pl.ujbtrinity.devplatform.service.impl.ProjectServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.io.IOException;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@RestController
public class UserProfileController {

    private final UserServiceImpl userService;
    private final ProjectServiceImpl projectService;

    public UserProfileController(UserServiceImpl userService, ProjectServiceImpl projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    private static final String USER_PROFILE_ENDPOINT = "/user/profile";
    private static final String USER_EMAIL_CHANGE_ENDPOINT = "/user/profile/email/change";
    private static final String USER_PASSWORD_CHANGE_ENDPOINT = "/user/profile/password/change";
    private static final String USER_FRAMEWORKS_EDITION_ENDPOINT = "/user/profile/frameworks/edit";
    private static final String USER_TECHNOLOGIES_EDITION_ENDPOINT = "/user/profile/technologies/edit";
    private static final String USER_PERSONALS_CHANGE_ENDPOINT = "/user/personals/edit";
    private static final String USER_UPLOAD_PHOTOGRAPHY_ENDPOINT = "/user/photo/upload";
    private static final String USER_PROFILE_PHOTOGRAPHY_ENDPOINT = "/user/photo/{id}";
    private static final String USER_LEAVE_PROJECT_ENDPOINT = "/user/project/leave/{id}";


    @GetMapping(USER_LEAVE_PROJECT_ENDPOINT)
    public String leaveProject(Principal principal, @PathVariable Long id){
        return projectService.leaveProject(principal.getName(), id);
    }




    @GetMapping(value = USER_PROFILE_ENDPOINT)
    public ResponseEntity<UserProfileDto> readProfile(Principal principal){
        UserProfileDto userProfile = userService.getUserProfile(principal.getName());
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }


    @GetMapping(value = USER_PROFILE_PHOTOGRAPHY_ENDPOINT)
    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable Long id) throws IOException {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(userService.getUserProfilePhoto(id));
    }


    @PostMapping(USER_EMAIL_CHANGE_ENDPOINT)
    public String editUserEmail(Principal principal,@Valid @RequestBody UserEmailChangeDto userProfileEditDto) {
        userService.editUserEmail(userProfileEditDto, principal.getName());
        return "User email changed";
    }

    @PostMapping(USER_PASSWORD_CHANGE_ENDPOINT)
    public String editUserPassword(Principal principal, @RequestBody PasswordChangeDto passwordChangeDto) {
        userService.editUserPassword(passwordChangeDto,principal.getName());
        return "User password changed";
    }


    @PostMapping(USER_TECHNOLOGIES_EDITION_ENDPOINT)
    public String addTechnologiesUsedByCurrentUser(Principal principal, @RequestBody UserTechnologyDto userTechnologyDto) {
        userService.editUserTechnologies(userTechnologyDto, principal.getName());
        return "User technologies updated";
    }

    @PostMapping(USER_FRAMEWORKS_EDITION_ENDPOINT)
    public String addFrameworksUsedByCurrentUser(Principal principal, @RequestBody UserFrameworkDto userFrameworkDto) {
        userService.editUserFrameworks(userFrameworkDto, principal.getName());
        return "User frameworks updated";
    }

    @PostMapping(USER_PERSONALS_CHANGE_ENDPOINT)
    public String editUserPersonals(Principal principal, @RequestBody UserProfileChangeDto userProfileChangeDto) {
        userService.editUserPersonals(userProfileChangeDto, principal.getName());
        return "User personals updated";
    }

    @PostMapping(value = USER_UPLOAD_PHOTOGRAPHY_ENDPOINT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addPhotography(Principal principal, @RequestParam("file") MultipartFile file) throws IOException {
        userService.savePhoto(file, principal.getName());
        return new ResponseEntity<>("file uploaded successfully", HttpStatus.OK);
    }
}
