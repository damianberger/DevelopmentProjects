package pl.ujbtrinity.devplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectCreateDto;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectSearchDto;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectViewDto;
import pl.ujbtrinity.devplatform.service.impl.ProjectServiceImpl;

import java.security.Principal;

@RestController
public class ProjectManagementController {

    private final ProjectServiceImpl projectService;

    public ProjectManagementController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    private static final String PROJECT_CREATION_ENDPOINT = "/project/create";
    private static final String PROJECT_VIEW_ENDPOINT = "/project/view/{id}";
    private static final String PROJECT_SEARCH_ENDPOINT = "/projects";

    @PostMapping(PROJECT_CREATION_ENDPOINT)
    public String createNewProject(@RequestBody ProjectCreateDto projectCreateDto, Principal principal){
        projectService.createProject(projectCreateDto,principal.getName());
        return "Project created";
    }

    @GetMapping(PROJECT_VIEW_ENDPOINT)
    public ResponseEntity<ProjectViewDto> viewProject(@PathVariable Long id){
        ProjectViewDto projectViewDto = projectService.viewProject(id);
        if(projectViewDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectViewDto,HttpStatus.OK);
    }

//    @GetMapping(PROJECT_SEARCH_ENDPOINT)
//    public ResponseEntity<ProjectSearchDto> searchProjects(){
//        return new ResponseEntity<>();
//    }
}
