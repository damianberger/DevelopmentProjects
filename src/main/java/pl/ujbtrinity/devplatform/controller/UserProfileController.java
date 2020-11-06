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

    private static final String USER_PROFILE_ENDPOINT = "/user/profile/{username}";
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
    private static final String USER_FRIEND_LIST_ENDPOINT = "/user/friends";
    private static final String USER_FRIEND_REQUEST_ACCEPT_ENDPOINT = "/user/friend/request/accept/{id}";
    private static final String USER_FRIEND_REQUEST_REMOVE_ENDPOINT = "/user/friend/request/decline/{username}";
    private static final String USER_FRIEND_REQUESTED_ENDPOINT = "/user/friend/requested";
    private static final String USER_FRIEND_RECEIVED_ENDPOINT = "/user/friend/received";
    private static final String USER_REMOVE_FRIEND_ENDPOINT = "/user/friend/remove/{username}";


    @GetMapping(value = USER_FRIEND_LIST_ENDPOINT)
    public Set<String> myFriendList(Principal principal) {
        return userService.userFriendList(principal.getName());
    }

//    @GetMapping(value = USER_FRIEND_REQUESTED_ENDPOINT)
//    public Set<FriendListRequested> sentFriendRequests(Principal principal) {
//
//    }
//
//    @GetMapping(value = USER_FRIEND_RECEIVED_ENDPOINT)
//    public Set<FriendListReceived> receivedFriendRequests(Principal principal) {
//
//    }
//
    @GetMapping(value = USER_FRIEND_REQUEST_REMOVE_ENDPOINT)
    public String friendRequestDenied(Principal principal, @PathVariable String username) {
        return userService.removeFriendRequest(username, principal.getName());
    }
//
//    @GetMapping(value = USER_FRIEND_REQUEST_ACCEPT_ENDPOINT)
//    public String friendRequestAccepted(Principal principal, @PathVariable Long id) {
//
//    }

    @GetMapping(value = USER_REMOVE_FRIEND_ENDPOINT)
    public String removeFriend(Principal principal, @PathVariable String username) {
        return userService.removeFriend(username, principal.getName());
    }

    @GetMapping(value = USER_PROFILE_ENDPOINT)
    public ResponseEntity<?> readProfile(@PathVariable String username, Principal principal) {
        if (userService.friendListCheck(username, principal.getName())) {
            return new ResponseEntity<>(userService.getPrivateUserProfile(username), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userService.getPublicUserProfile(username), HttpStatus.OK);
        }
    }

    @GetMapping(value = USER_PROFILE_PHOTOGRAPHY_ENDPOINT)
    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable Long id) throws IOException {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(userService.getUserProfilePhoto(id));
    }


    @PostMapping(value = USER_EMAIL_CHANGE_ENDPOINT)
    public String editUserEmail(Principal principal, @Valid @RequestBody UserEmailChangeDto userProfileEditDto) {
        userService.editUserEmail(userProfileEditDto, principal.getName());
        return "User email changed";
    }

    @PostMapping(value = USER_PASSWORD_CHANGE_ENDPOINT)
    public String editUserPassword(Principal principal, @RequestBody PasswordChangeDto passwordChangeDto) {
        userService.editUserPassword(passwordChangeDto, principal.getName());
        return "User password changed";
    }


    @PostMapping(value = USER_TECHNOLOGIES_EDITION_ENDPOINT)
    public String addTechnologiesUsedByCurrentUser(Principal principal, @RequestBody UserTechnologyDto userTechnologyDto) {
        userService.editUserTechnologies(userTechnologyDto, principal.getName());
        return "User technologies updated";
    }

    @PostMapping(value = USER_FRAMEWORKS_EDITION_ENDPOINT)
    public String addFrameworksUsedByCurrentUser(Principal principal, @RequestBody UserFrameworkDto userFrameworkDto) {
        userService.editUserFrameworks(userFrameworkDto, principal.getName());
        return "User frameworks updated";
    }

    @PostMapping(value = USER_PERSONALS_CHANGE_ENDPOINT)
    public String editUserPersonals(Principal principal, @RequestBody UserProfileChangeDto userProfileChangeDto) {
        userService.editUserPersonals(userProfileChangeDto, principal.getName());
        return "User personals updated";
    }

    @PostMapping(value = USER_UPLOAD_PHOTOGRAPHY_ENDPOINT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addPhotography(Principal principal, @RequestParam("file") MultipartFile file) throws IOException {
        userService.savePhoto(file, principal.getName());
        return new ResponseEntity<>("file uploaded successfully", HttpStatus.OK);
    }

    @GetMapping(value = USER_PROJECT_INVITATIONS_ENDPOINT)
    public ResponseEntity<Set<ProjectInvitationDto>> userProjectInvitations(Principal principal) {
        return new ResponseEntity<>(projectService.userProjectInvitations(principal.getName()), HttpStatus.FOUND);
    }

    @GetMapping(value = USER_PROJECT_INVITATION_ACCEPT_ENDPOINT)
    public String acceptProjectInvitation(Principal principal, @PathVariable Long id) {
        return projectService.acceptProjectInvitation(principal.getName(), id);
    }


    @GetMapping(value = USER_PROJECT_INVITATION_DECLINE_ENDPOINT)
    public String declineProjectInvitation(Principal principal, @PathVariable Long id) {
        return projectService.declineProjectInvitation(principal.getName(), id);
    }

    @GetMapping(value = USER_LEAVE_PROJECT_ENDPOINT)
    public String leaveProject(Principal principal, @PathVariable Long id) {
        return projectService.leaveProject(principal.getName(), id);
    }
}
