package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.dto.projectDto.ProjectViewDto;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;

import java.util.List;

public interface ProjectService {
    void createProject(Project project);
    void updateProject (Project project);
    ProjectViewDto viewProject(Long id);
    List<Project> findAll();
    List<Project> findByTechnologyUsed(Technology technology);
}
