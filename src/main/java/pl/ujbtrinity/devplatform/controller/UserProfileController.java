package pl.ujbtrinity.devplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectInvitationDto;
import pl.ujbtrinity.devplatform.dto.userDto.*;
import pl.ujbtrinity.devplatform.service.impl.ProjectServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.io.IOException;
import javax.validation.Valid;

import java.security.Principal;
import java.util.Set;

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
    private static final String USER_PROJECT_INVITATIONS_ENDPOINT = "/user/project/invitations";
    private static final String USER_PROJECT_INVITATION_ACCEPT_ENDPOINT = "/user/project/accept/{id}";
    private static final String USER_PROJECT_INVITATION_DECLINE_ENDPOINT = "/user/project/decline/{id}";

    @GetMapping(USER_PROJECT_INVITATION_ACCEPT_ENDPOINT)
    public ResponseEntity<String> acceptProjectInvitation(Principal principal,@PathVariable Long id) {
        projectService.acceptProjectInvitation(principal.getName(), id);
        return new ResponseEntity<>("Project invitation accepted.", HttpStatus.OK);
    }


    @GetMapping(USER_PROJECT_INVITATION_DECLINE_ENDPOINT)
    public ResponseEntity<String> declineProjectInvitation(Principal principal,@PathVariable Long id) {
        projectService.declineProjectInvitation(principal.getName(), id);
        return new ResponseEntity<>("You have declined project invitation.", HttpStatus.OK);
    }


    @GetMapping(value = USER_PROFILE_ENDPOINT)
    public ResponseEntity<UserProfileDto> readProfile(Principal principal) {
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
    public ResponseEntity<String> editUserEmail(Principal principal, @Valid @RequestBody UserEmailChangeDto userProfileEditDto) {
        userService.editUserEmail(userProfileEditDto, principal.getName());
        return new ResponseEntity<>("You have changed your email.", HttpStatus.OK);
    }

    @PostMapping(USER_PASSWORD_CHANGE_ENDPOINT)
    public ResponseEntity<String> editUserPassword(Principal principal, @RequestBody PasswordChangeDto passwordChangeDto) {
        userService.editUserPassword(passwordChangeDto, principal.getName());
        return new ResponseEntity<>("You have changed your password.", HttpStatus.OK);
    }


    @PostMapping(USER_TECHNOLOGIES_EDITION_ENDPOINT)
    public ResponseEntity<String> addTechnologiesUsedByCurrentUser(Principal principal, @RequestBody UserTechnologyDto userTechnologyDto) {
        userService.editUserTechnologies(userTechnologyDto, principal.getName());
        return new ResponseEntity<>("You have changed your profile technologies used.", HttpStatus.OK);
    }

    @PostMapping(USER_FRAMEWORKS_EDITION_ENDPOINT)
    public ResponseEntity<String> addFrameworksUsedByCurrentUser(Principal principal, @RequestBody UserFrameworkDto userFrameworkDto) {
        userService.editUserFrameworks(userFrameworkDto, principal.getName());
        return new ResponseEntity<>("You have changed your profile frameworks used.", HttpStatus.OK);
    }

    @PostMapping(USER_PERSONALS_CHANGE_ENDPOINT)
    public ResponseEntity<String> editUserPersonals(Principal principal, @RequestBody UserProfileChangeDto userProfileChangeDto) {
        userService.editUserPersonals(userProfileChangeDto, principal.getName());
        return new ResponseEntity<>("You have changed your personal data.", HttpStatus.OK);
    }

    @PostMapping(value = USER_UPLOAD_PHOTOGRAPHY_ENDPOINT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addPhotography(Principal principal, @RequestParam("file") MultipartFile file) throws IOException {
        userService.savePhoto(file, principal.getName());
        return new ResponseEntity<>("file uploaded successfully", HttpStatus.OK);
    }

    @GetMapping(USER_PROJECT_INVITATIONS_ENDPOINT)
    public ResponseEntity<Set<ProjectInvitationDto>> userProjectInvitations(Principal principal) {
        return new ResponseEntity<>(projectService.userProjectInvitations(principal.getName()), HttpStatus.FOUND);
    }

    @GetMapping(USER_LEAVE_PROJECT_ENDPOINT)
    public ResponseEntity<String> leaveProject(Principal principal, @PathVariable Long id) {
        projectService.leaveProject(principal.getName(), id);
        return new ResponseEntity<>("You have left project.", HttpStatus.OK);
    }
}

