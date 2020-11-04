package pl.ujbtrinity.devplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ujbtrinity.devplatform.dto.projectDto.*;
import pl.ujbtrinity.devplatform.service.impl.ProjectServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
public class ProjectManagementController {

    private final ProjectServiceImpl projectService;
    private final UserServiceImpl userService;

    public ProjectManagementController(ProjectServiceImpl projectService, UserServiceImpl userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    private static final String PROJECT_CREATION_ENDPOINT = "/project/create";
    private static final String PROJECT_VIEW_ENDPOINT = "/project/view/{id}";
    private static final String PROJECT_SEARCH_ENDPOINT = "/projects";
    private static final String PROJECT_DELETE_ENDPOINT = "/project/delete/{id}";
    private static final String PROJECT_UPDATE_ENDPOINT = "/project/update";
    private static final String PROJECT_JOIN_ENDPOINT = "/project/join/{id}";
    private static final String PROJECT_LEAVE_ENDPOINT = "/project/leave/{id}";
    private static final String PROJECT_INVITE_ENDPOINT = "/project/invite/user={userId}&project={projectId}";
    private static final String PROJECT_PENDING_INVITATIONS_ENDPOINT = "/project/invitations/{id}";


    @PostMapping(PROJECT_CREATION_ENDPOINT)
    public String createNewProject(@RequestBody ProjectCreateDto projectCreateDto, Principal principal) {
        projectService.createProject(projectCreateDto, principal.getName());
        return "Project created";
    }

    @GetMapping(PROJECT_VIEW_ENDPOINT)
    public ResponseEntity<ProjectViewDto> viewProject(@PathVariable Long id) {
        ProjectViewDto projectViewDto = projectService.viewProject(id);
        if (projectViewDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectViewDto, HttpStatus.OK);
    }

    @GetMapping(PROJECT_SEARCH_ENDPOINT)
    public ResponseEntity<List<ProjectSearchReceivedDto>> searchProjects() {
        List<ProjectSearchReceivedDto> projectFound = projectService.projectSearch();
        return new ResponseEntity<>(projectFound, HttpStatus.OK);
    }

    @DeleteMapping(PROJECT_DELETE_ENDPOINT)
    public String deleteProject(Principal principal, @PathVariable Long id) {
        return projectService.deleteProject(principal.getName(), id);
    }

    @PostMapping(PROJECT_UPDATE_ENDPOINT)
    public String updateProject(@RequestBody ProjectUpdateDto projectUpdateDto, Principal principal) {
        return projectService.updateProject(projectUpdateDto, principal.getName());
    }

    @GetMapping(PROJECT_JOIN_ENDPOINT)
    public String joinProject(@PathVariable Long id, Principal principal) {
        return projectService.joinProject(principal.getName(), id);
    }

    @GetMapping(PROJECT_LEAVE_ENDPOINT)
    public String leaveProject(@PathVariable Long id, Principal principal) {
        return projectService.leaveProject(principal.getName(), id);
    }

    @PostMapping(PROJECT_INVITE_ENDPOINT)
    public String inviteUser(Principal principal, @PathVariable Long userId, @PathVariable Long projectId) {
        return projectService.inviteUser(principal.getName(), userId, projectId);
    }

    @GetMapping(PROJECT_PENDING_INVITATIONS_ENDPOINT)
    public ResponseEntity<Set<ProjectInvitationDto>> pendingProjectInvitations(@PathVariable Long id, Principal principal) {
        Set<ProjectInvitationDto> projectInvitations = projectService.projectInvitations(id, principal.getName());
        return new ResponseEntity<>(projectInvitations, HttpStatus.FOUND);
    }

}
