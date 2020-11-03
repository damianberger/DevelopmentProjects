package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.dto.projectDto.*;
import pl.ujbtrinity.devplatform.entity.Project;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProjectService {
    Optional<Project> findById(Long id);
    void createProject(ProjectCreateDto projectCreateDto, String username);
    ProjectViewDto viewProject(Long id);
    List<ProjectSearchReceivedDto> projectSearch();
    void updateProject (ProjectUpdateDto projectUpdateDto);
    String deleteProject (String username, Long id);
    String joinProject(String username, Long id);
    String leaveProject(String username, Long id);
    String inviteUser(String username, Long userId, Long projectId);
    Set<ProjectInvitationDto> projectInvitations(Long id, String username);
}
