package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectCreateDto;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectSearchReceivedDto;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectSearchRequestedDto;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectViewDto;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.repository.FrameworkRepository;
import pl.ujbtrinity.devplatform.repository.ProjectRepository;
import pl.ujbtrinity.devplatform.repository.TechnologyRepository;
import pl.ujbtrinity.devplatform.repository.UserRepository;
import pl.ujbtrinity.devplatform.service.ProjectService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final FrameworkRepository frameworkRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, FrameworkRepository frameworkRepository, TechnologyRepository technologyRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.frameworkRepository = frameworkRepository;
        this.technologyRepository = technologyRepository;
    }

    @Override
    public void createProject(ProjectCreateDto projectCreateDto, String username) {
        User user = userRepository.findByUsername(username);
        Project project = projectCreateDto.toProject();
        project.setCreator(user);

        Set<String> frameworks = frameworkRepository.findAll()
                .stream().map(Framework::getName)
                .collect(Collectors.toSet());
        Set<String> projectFrameworks = frameworks.stream()
                .distinct()
                .filter(projectCreateDto.getFrameworks()::contains)
                .collect(Collectors.toSet());
        Set<Framework> frameworksToDB = new HashSet<>();
        for(String frameworkToDb : projectFrameworks){
            frameworksToDB.add(frameworkRepository.findByName(frameworkToDb));
        }
        project.setFrameworksUsed(frameworksToDB);

        Set<String> technologies = technologyRepository.findAll()
                .stream().map(Technology::getName)
                .collect(Collectors.toSet());
        Set<String> projectTechnologies = technologies.stream()
                .distinct()
                .filter(projectCreateDto.getTechnologies()::contains)
                .collect(Collectors.toSet());
        Set<Technology> technologiesToDB = new HashSet<>();
        for (String technologyToDB : projectTechnologies) {
            technologiesToDB.add(technologyRepository.findByName(technologyToDB));
        }
        project.setTechnologiesUsed(technologiesToDB);
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
    public List<ProjectSearchReceivedDto> projectSearch(ProjectSearchRequestedDto projectSearchRequestedDto) {
        List<Project> projects = projectRepository.findAll();
        List<ProjectSearchReceivedDto> projectsFound = new ArrayList<>();
        for (Project project: projects) {
        ProjectSearchReceivedDto projectReceived = ProjectSearchReceivedDto.fromProject(project);
        projectsFound.add(projectReceived);
        }
        return projectsFound.stream()
                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getName().matches(projectSearchRequestedDto.getName()))
                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getCreated().isAfter(projectSearchRequestedDto.getCreatedAfter()))
                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getCreated().isBefore(projectSearchRequestedDto.getCreatedBefore()))
//                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getTechnologies().equals(projectSearchRequestedDto.getTechnologies()))
//                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getFrameworks().equals(projectSearchRequestedDto.getFrameworks()))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectViewDto viewProject(Long id) {
        return ProjectViewDto.fromProject(projectRepository.getOne(id));
    }
}
