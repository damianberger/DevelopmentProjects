package pl.ujbtrinity.devplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ujbtrinity.devplatform.dto.projectDto.*;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.service.impl.ProjectServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


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

    @PostMapping(PROJECT_SEARCH_ENDPOINT)
    public ResponseEntity<List<ProjectSearchReceivedDto>> searchProjects(@RequestBody ProjectSearchRequestedDto projectSearchRequestedDto) {
        List<ProjectSearchReceivedDto> projectFound = projectService.projectSearch(projectSearchRequestedDto);
        return new ResponseEntity<>(projectFound, HttpStatus.OK);
    }

    @DeleteMapping(PROJECT_DELETE_ENDPOINT)
    public ResponseEntity<String> deleteProject(@PathVariable Long id, Principal principal) throws InterruptedException {
        User projectOwner = userService.findByUsername(principal.getName());
        Optional<Project> project = projectService.findById(id);
        if(!project.isPresent()){
            return new ResponseEntity<>("Project doesn't exist",HttpStatus.NO_CONTENT);
        }else {
            if (projectOwner.getId().equals(project.get().getCreator().getId())) {
                projectService.removeUsersFromProject(id);
                projectService.deleteProject(id);
                return new ResponseEntity<>("Project deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Access denied", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping(PROJECT_UPDATE_ENDPOINT)
    public ResponseEntity<String> updateProject(@RequestBody ProjectUpdateDto projectUpdateDto, Principal principal) {
        User projectOwner = userService.findByUsername(principal.getName());
        Optional<Project> project = projectService.findById(projectUpdateDto.getId());
        if(!project.isPresent()){
            return new ResponseEntity<>("Project doesn't exist",HttpStatus.NO_CONTENT);
        }else {
            if (projectOwner.getId().equals(project.get().getCreator().getId())) {
                projectService.updateProject(projectUpdateDto);
                return new ResponseEntity<>("Project updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Access denied", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
