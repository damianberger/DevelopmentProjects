package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.dto.projectDto.ProjectCreateDto;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectSearchReceivedDto;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectSearchRequestedDto;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectViewDto;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;

import java.util.List;

public interface ProjectService {
    void createProject(ProjectCreateDto projectCreateDto, String username);
    ProjectViewDto viewProject(Long id);
    List<ProjectSearchReceivedDto> projectSearch(ProjectSearchRequestedDto projectSearchRequestedDto);
    void updateProject (Project project);
}
