package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.dto.projectDto.*;
import pl.ujbtrinity.devplatform.entity.*;
import pl.ujbtrinity.devplatform.model.InvitationStatus;
import pl.ujbtrinity.devplatform.repository.*;
import pl.ujbtrinity.devplatform.service.ProjectService;


import java.time.LocalDateTime;
import java.util.*;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final FrameworkRepository frameworkRepository;
    private final TechnologyRepository technologyRepository;
    private final ProjectInvitationRepository projectInvitationRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, FrameworkRepository frameworkRepository, TechnologyRepository technologyRepository, ProjectInvitationRepository projectInvitationRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.frameworkRepository = frameworkRepository;
        this.technologyRepository = technologyRepository;
        this.projectInvitationRepository = projectInvitationRepository;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public void createProject(ProjectCreateDto projectCreateDto, String username) {
        User user = userRepository.findByUsername(username);
        Project project = projectCreateDto.toProject();
        project.setCreator(user);
        Set<Technology> projectTechnologies = new HashSet<>();
        for(String technology: projectCreateDto.getTechnologies()){
            projectTechnologies.add(technologyRepository.findByName(technology));
        }
        project.setTechnologiesUsed(projectTechnologies);
        Set<Framework> projectFrameworks = new HashSet<>();
        for(String framework: projectCreateDto.getFrameworks()){
            projectFrameworks.add(frameworkRepository.findByName(framework));
        }
        project.setFrameworksUsed(projectFrameworks);
        projectRepository.save(project);
    }

    @Override
    public List<ProjectSearchReceivedDto> projectSearch() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectSearchReceivedDto> projectsFound = new ArrayList<>();
        for (Project project: projects) {
        ProjectSearchReceivedDto projectReceived = ProjectSearchReceivedDto.fromProject(project);
        projectsFound.add(projectReceived);
        }
        return projectsFound;
    }

    @Override
    public ProjectViewDto viewProject(Long id) {
        return ProjectViewDto.fromProject(projectRepository.getOne(id));
    }

    @Override
    public void updateProject(ProjectUpdateDto projectUpdateDto) {
        Project project = projectRepository.getOne(projectUpdateDto.getId());
        project.setDescription(projectUpdateDto.getDescription());
        Set<Technology> projectTechnologies = new HashSet<>();
        for(String technology: projectUpdateDto.getTechnologiesUsed()){
            projectTechnologies.add(technologyRepository.findByName(technology));
        }
        project.setTechnologiesUsed(projectTechnologies);

        Set<Framework> projectFrameworks = new HashSet<>();
        for(String framework: projectUpdateDto.getFrameworksUsed()){
            projectFrameworks.add(frameworkRepository.findByName(framework));
        }
        project.setFrameworksUsed(projectFrameworks);

        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
        projectInvitationRepository.removeProjectInvitationsByProject(id);
    }

    @Override
    public String joinProject(String username, Long id) {
        User user = userRepository.findByUsername(username);
        Optional<Project> project = projectRepository.findById(id);
        if (!project.isPresent()) {
            return "Project doesn't exist";
        } else {
            Set<User> projectUsers = project.get().getUsers();
            if (projectUsers.contains(user)) {
                return "You are already participating in this project";
            } else {
                projectUsers.add(user);
                projectRepository.save(project.get());
                return "You are now participating in this project";
            }
        }

    }

    @Override
    public String leaveProject(String username, Long id) {
        User user = userRepository.findByUsername(username);
        Optional<Project> project = projectRepository.findById(id);
        if (!project.isPresent()) {
            return "Project doesn't exist";
        } else {
            Set<User> projectUsers = project.get().getUsers();
            if (!projectUsers.contains(user)) {
                return "You are not participating in selected project";
            } else {
                projectUsers.remove(user);
                projectRepository.save(project.get());
                return "You have left selected project";
            }
        }
    }

    @Override
    public String inviteUser(String username, Long userId, Long projectId) {
        User invitingUser = userRepository.findByUsername(username);
        Optional<User> invitedUser = userRepository.findById(userId);
        if(!invitedUser.isPresent()) {
            return "User by this ID doesn't exist";
        }
        Optional<Project> project = projectRepository.findById(projectId);
        if(!project.isPresent()){
            return "Project by this ID doesn't exist";
        }
        if(!invitingUser.equals(project.get().getCreator())){
            return "You are not an owner of selected project";
        }
        ProjectInvitation invite = new ProjectInvitation();
        invite.setInvitingUser(invitingUser);
        invite.setInvitedUser(invitedUser.get());
        invite.setProject(project.get());
        invite.setStatus(InvitationStatus.ACTIVE);
        invite.setCreated(LocalDateTime.now());
        projectInvitationRepository.save(invite);
        return "Invitation pending";
    }

    @Override
    public void removeUsersFromProject(Long id) {
        userRepository.removeUsersFromProject(id);
    }
}
