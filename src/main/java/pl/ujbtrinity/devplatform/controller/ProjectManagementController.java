package pl.ujbtrinity.devplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ujbtrinity.devplatform.dto.projectDto.*;
import pl.ujbtrinity.devplatform.service.impl.ProjectServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
public class ProjectManagementController {

    private final ProjectServiceImpl projectService;

    public ProjectManagementController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    private static final String PROJECT_CREATION_ENDPOINT = "/api/project/create";
    private static final String PROJECT_VIEW_ENDPOINT = "/api/project/view/{id}";
    private static final String PROJECT_SEARCH_ENDPOINT = "/api/project/all";
    private static final String PROJECT_DELETE_ENDPOINT = "/api/project/delete/{id}";
    private static final String PROJECT_UPDATE_ENDPOINT = "/api/project/update";

    private static final String PROJECT_JOIN_ENDPOINT = "/api/project/join/{id}";
    private static final String PROJECT_LEAVE_ENDPOINT = "/api/project/leave/{id}";
    private static final String PROJECT_INVITE_ENDPOINT = "/api/project/invite/user={userId}&project={projectId}";
    private static final String PROJECT_PENDING_INVITATIONS_ENDPOINT = "/api/project/invitations/{id}";


    @PostMapping(PROJECT_CREATION_ENDPOINT)
    public ResponseEntity<String> createNewProject(@RequestBody ProjectCreateDto projectCreateDto, Principal principal) {
        projectService.createProject(projectCreateDto, principal.getName());
        return new ResponseEntity<>("Project successfully created.", HttpStatus.OK);
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
    public ResponseEntity<String> deleteProject(Principal principal, @PathVariable Long id) {
        projectService.deleteProject(principal.getName(), id);
        return new ResponseEntity<>("Project deleted.", HttpStatus.OK);

    }

    @PostMapping(PROJECT_UPDATE_ENDPOINT)
    public ResponseEntity<String> updateProject(@RequestBody ProjectUpdateDto projectUpdateDto, Principal principal) {
        projectService.updateProject(projectUpdateDto, principal.getName());
        return new ResponseEntity<>("Project successfully updated.", HttpStatus.OK);
    }

    @GetMapping(PROJECT_JOIN_ENDPOINT)
    public ResponseEntity<String> joinProject(@PathVariable Long id, Principal principal) {
        projectService.joinProject(principal.getName(), id);
        return new ResponseEntity<>("You are in this project now.", HttpStatus.OK);
    }

    @GetMapping(PROJECT_LEAVE_ENDPOINT)
    public ResponseEntity<String> leaveProject(@PathVariable Long id, Principal principal) {
        projectService.leaveProject(principal.getName(), id);
        return new ResponseEntity<>("You have left this project", HttpStatus.OK);
    }

    @PostMapping(PROJECT_INVITE_ENDPOINT)
    public ResponseEntity<String> inviteUser(Principal principal, @PathVariable Long userId, @PathVariable Long projectId) {
        return new ResponseEntity<>(projectService.inviteUser(principal.getName(), userId, projectId), HttpStatus.OK);
    }

    @GetMapping(PROJECT_PENDING_INVITATIONS_ENDPOINT)
    public ResponseEntity<Set<ProjectInvitationDto>> pendingProjectInvitations(@PathVariable Long id, Principal principal) {
        Set<ProjectInvitationDto> projectInvitations = projectService.projectInvitations(id, principal.getName());
        return new ResponseEntity<>(projectInvitations, HttpStatus.FOUND);
    }

}
