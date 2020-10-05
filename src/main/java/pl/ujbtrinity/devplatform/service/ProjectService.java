package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;

import java.util.List;

public interface ProjectService {
    void createProject(Project project);
    void updateProject (Project project);
    List<Project> findAll();
    List<Project> findByTechnologyUsed(Technology technology);
    Project readProject(long id);
}
