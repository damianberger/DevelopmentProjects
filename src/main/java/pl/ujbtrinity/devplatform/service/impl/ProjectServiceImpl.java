package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.dto.projectDto.*;
import pl.ujbtrinity.devplatform.entity.*;
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
        for (String technology : projectCreateDto.getTechnologies()) {
            projectTechnologies.add(technologyRepository.findByName(technology));
        }
        project.setTechnologiesUsed(projectTechnologies);
        Set<Framework> projectFrameworks = new HashSet<>();
        for (String framework : projectCreateDto.getFrameworks()) {
            projectFrameworks.add(frameworkRepository.findByName(framework));
        }
        project.setFrameworksUsed(projectFrameworks);
        projectRepository.save(project);
    }

    @Override
    public List<ProjectSearchReceivedDto> projectSearch() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectSearchReceivedDto> projectsFound = new ArrayList<>();
        for (Project project : projects) {
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
        for (String technology : projectUpdateDto.getTechnologiesUsed()) {
            projectTechnologies.add(technologyRepository.findByName(technology));
        }
        project.setTechnologiesUsed(projectTechnologies);

        Set<Framework> projectFrameworks = new HashSet<>();
        for (String framework : projectUpdateDto.getFrameworksUsed()) {
            projectFrameworks.add(frameworkRepository.findByName(framework));
        }
        project.setFrameworksUsed(projectFrameworks);

        projectRepository.save(project);
    }

    @Override
    public String deleteProject(String username, Long id) {
        User projectOwner = userRepository.findByUsername(username);
        Optional<Project> project = projectRepository.findById(id);
        if (!project.isPresent()) {
            return "Project doesn't exist";
        }
        if (!project.get().getCreator().equals(projectOwner)) {
            return "You are not an owner of this project";
        }
        projectRepository.removeUsersFromProject(id);
        projectInvitationRepository.removeProjectInvitationsByProject(id);
        projectRepository.deleteById(id);
        return "Project successfully deleted";
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
        if (!invitedUser.isPresent()) {
            return "User by this ID doesn't exist";
        }
        Optional<Project> project = projectRepository.findById(projectId);
        if (!project.isPresent()) {
            return "Project by this ID doesn't exist";
        }
        if (project.get().getCreator().equals(invitingUser) || project.get().getUsers().contains(invitingUser)) {
            ProjectInvitation duplicateCheck = projectInvitationRepository.findByUserAndProject(userId, projectId);
            if (duplicateCheck != null) {
                return "This user has already been invited to this project";
            }
            ProjectInvitation invite = new ProjectInvitation();
            invite.setInvitingUser(invitingUser);
            invite.setInvitedUser(invitedUser.get());
            invite.setProject(project.get());
            invite.setCreated(LocalDateTime.now());
            projectInvitationRepository.save(invite);
            return "Invitation pending";
        } else {
            return "You are not an owner of selected project";
        }
    }

    @Override
    public Set<ProjectInvitationDto> projectInvitations(Long id, String username) {
        Optional<Project> project = projectRepository.findById(id);
        if (!project.isPresent()) {
            return null;
        }
        User projectOwner = userRepository.findByUsername(username);
        if (!project.get().getCreator().equals(projectOwner)) {
            return null;
        }
        Set<ProjectInvitation> invitations = projectInvitationRepository.findByProject(id);
        return getProjectInvitationDtos(invitations);
    }

    @Override
    public Set<ProjectInvitationDto> userProjectInvitations(String username) {
        Set<ProjectInvitation> invitations = projectInvitationRepository.findByUser(userRepository.findByUsername(username).getId());
        return getProjectInvitationDtos(invitations);
    }

    private Set<ProjectInvitationDto> getProjectInvitationDtos(Set<ProjectInvitation> invitations) {
        Set<ProjectInvitationDto> invitationDtos = new HashSet<>();
        for (ProjectInvitation invitation : invitations) {
            ProjectInvitationDto invitationDto = new ProjectInvitationDto();
            invitationDto.setInvitationId(invitation.getId());
            invitationDto.setInvitationDateTime(invitation.getCreated());
            invitationDto.setInvitingUser(invitation.getInvitingUser().getUsername());
            invitationDto.setInvitedUser(invitation.getInvitedUser().getUsername());
            invitationDto.setProjectName(invitation.getProject().getName());
            invitationDtos.add(invitationDto);
        }
        return invitationDtos;
    }

    @Override
    public String acceptProjectInvitation(String username, Long projectId) {
        User user = userRepository.findByUsername(username);
        Optional<Project> project = projectRepository.findById(projectId);
        if (!project.isPresent()) {
            return "Project doesn't exist";
        }
        ProjectInvitation invitationCheck = projectInvitationRepository.findByUserAndProject(user.getId(), projectId);
        if (invitationCheck == null) {
            return "You have not been invited to this project";
        } else {
            projectInvitationRepository.deleteById(invitationCheck.getId());
            Set<User> projectParticipants = project.get().getUsers();
            projectParticipants.add(user);
            project.get().setUsers(projectParticipants);
            projectRepository.save(project.get());
            return "You have successfully joined this project";
        }
    }

    @Override
    public String declineProjectInvitation(String username, Long projectId) {
        User user = userRepository.findByUsername(username);
        Optional<Project> project = projectRepository.findById(projectId);
        if (!project.isPresent()) {
            return "Project doesn't exist";
        }
        ProjectInvitation invitationCheck = projectInvitationRepository.findByUserAndProject(user.getId(), projectId);
        if (invitationCheck == null) {
            return "You have not been invited to this project";
        }else {
            projectInvitationRepository.deleteById(invitationCheck.getId());
            return "You have declined participating in this project";
        }
    }
}
