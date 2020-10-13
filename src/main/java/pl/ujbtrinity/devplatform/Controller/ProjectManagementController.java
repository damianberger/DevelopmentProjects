package pl.ujbtrinity.devplatform.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ujbtrinity.devplatform.service.impl.ProjectServiceImpl;

@RestController
public class ProjectManagementController {

    private final ProjectServiceImpl projectService;

    public ProjectManagementController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    private static final String PROJECT_CREATION_ENDPOINT = "/project/create";

    @GetMapping(PROJECT_CREATION_ENDPOINT)
    public String createNewProject(){
        return "Test";
    }
}
