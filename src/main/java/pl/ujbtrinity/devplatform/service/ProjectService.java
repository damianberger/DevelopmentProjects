package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.dto.projectDto.*;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Project> findById(Long id);
    void createProject(ProjectCreateDto projectCreateDto, String username);
    ProjectViewDto viewProject(Long id);
    List<ProjectSearchReceivedDto> projectSearch(ProjectSearchRequestedDto projectSearchRequestedDto);
    void updateProject (ProjectUpdateDto projectUpdateDto);
    void deleteProject (Long id);
}
