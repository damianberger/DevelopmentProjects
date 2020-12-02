package pl.ujbtrinity.devplatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.service.FrameworkService;
import pl.ujbtrinity.devplatform.service.ProjectService;
import pl.ujbtrinity.devplatform.service.TechnologyService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final FrameworkService frameworkService;
    private final TechnologyService technologyService;
    private final ProjectService projectService;

    public AdminController(FrameworkService frameworkService, TechnologyService technologyService, ProjectService projectService) {
        this.frameworkService = frameworkService;
        this.technologyService = technologyService;
        this.projectService = projectService;
    }

    @PostMapping("/framework/add")
    public HttpStatus addFramework (@RequestBody Framework framework) {
        frameworkService.createFramework(framework);
        return HttpStatus.OK;
    }

    @GetMapping("/framework/all")
    public List<Framework> findAllFrameworks () {
        return frameworkService.findAll();
    }

    @DeleteMapping("/framework/delete/{id}")
    public HttpStatus deleteFrameworkById (@PathVariable Long id) {
        frameworkService.deleteById(id);
        return HttpStatus.OK;
    }

    @PutMapping("/framework/update/{id}")
    public HttpStatus updateFramework (@RequestBody Framework framework) {
        frameworkService.updateFramework(framework);
        return HttpStatus.OK;
    }

    @PostMapping("/technology/add")
    public HttpStatus addTechnology (@RequestBody Technology technology) {
        technologyService.createTechnology(technology);
        return HttpStatus.OK;
    }

    @GetMapping("/technology/all")
    public List<Technology> findAllTechnologies () {
        return technologyService.findAll();
    }

    @DeleteMapping("/technology/delete/{id}")
    public HttpStatus deleteTechnologyById (@PathVariable Long id) {
        technologyService.deleteById(id);
        return HttpStatus.OK;
    }

    @PutMapping("/technology/update/{id}")
    public HttpStatus updateTechnology (@RequestBody Technology technology) {
        technologyService.updateTechnology(technology);
        return HttpStatus.OK;
    }

    @GetMapping("/project/all")
    public List<Project> findAllProjects() {
        return projectService.findAll();
    }

    @DeleteMapping("/project/delete/{id}")
    public HttpStatus deleteProjectById (@PathVariable Long id) {
        projectService.deleteById(id);
        return HttpStatus.OK;
    }

}
