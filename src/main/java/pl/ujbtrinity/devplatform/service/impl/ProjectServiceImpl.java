package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.repository.ProjectRepository;
import pl.ujbtrinity.devplatform.service.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void createProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void updateProject(Project project) {
        Project projectFromDb = projectRepository.getOne(project.getId());
        projectFromDb.setName(project.getName());
        projectFromDb.setDescription(project.getDescription());
        projectFromDb.setTechnologiesUsed(project.getTechnologiesUsed());
        projectFromDb.setFrameworksUsed(project.getFrameworksUsed());
        projectFromDb.setUsers(project.getUsers());
        projectRepository.save(projectFromDb);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findByTechnologyUsed(Technology technology) {
        return projectRepository.findByTechnologiesUsedEquals(technology);
    }

    @Override
    public Project readProject(long id) {
        return projectRepository.getOne(id);
    }
}
