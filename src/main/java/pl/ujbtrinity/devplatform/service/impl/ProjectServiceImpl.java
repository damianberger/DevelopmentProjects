package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.dto.projectDto.*;
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
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
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
    public List<ProjectSearchReceivedDto> projectSearch(ProjectSearchRequestedDto projectSearchRequestedDto) {
        List<Project> projects = projectRepository.findAll();
        List<ProjectSearchReceivedDto> projectsFound = new ArrayList<>();
        for (Project project: projects) {
        ProjectSearchReceivedDto projectReceived = ProjectSearchReceivedDto.fromProject(project);
        projectsFound.add(projectReceived);
        }
        return projectsFound.stream()
                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getName().toLowerCase().contains(projectSearchRequestedDto.getName().toLowerCase()))
                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getCreated().isAfter(projectSearchRequestedDto.getCreatedAfter()))
                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getCreated().isBefore(projectSearchRequestedDto.getCreatedBefore()))
                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getTechnologies().containsAll(projectSearchRequestedDto.getTechnologies()))
                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getFrameworks().containsAll(projectSearchRequestedDto.getFrameworks()))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectViewDto viewProject(Long id) {
        return ProjectViewDto.fromProject(projectRepository.getOne(id));
    }

    @Override
    public void updateProject(ProjectUpdateDto projectUpdateDto) {
        Project project = projectRepository.getOne(projectUpdateDto.getId());
        project.setDescription(projectUpdateDto.getDescription());

        Set<String> technologies = (technologyRepository.findAll())
                .stream().map(Technology::getName)
                .collect(Collectors.toSet());
        Set<String> projectTechnologies = technologies.stream()
                .distinct()
                .filter(projectUpdateDto.getTechnologiesUsed()::contains)
                .collect(Collectors.toSet());
        Set<Technology> technologiesToDB = new HashSet<>();
        for (String technologyToDB : projectTechnologies) {
            technologiesToDB.add(technologyRepository.findByName(technologyToDB));
        }
        project.setTechnologiesUsed(technologiesToDB);

        Set<String> frameworks = (frameworkRepository.findAll()
                .stream().map(Framework::getName)
                .collect(Collectors.toSet()));
        Set<String> projectFrameworks = frameworks.stream()
                .distinct()
                .filter(projectUpdateDto.getFrameworksUsed()::contains)
                .collect(Collectors.toSet());
        Set<Framework> frameworksToDB = new HashSet<>();
        for (String frameworkToDB : projectFrameworks) {
            frameworksToDB.add(frameworkRepository.findByName(frameworkToDB));
        }
        project.setFrameworksUsed(frameworksToDB);

        projectRepository.save(project);
    }



    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void removeUsersFromProject(Long id) {
        userRepository.removeUsersFromProject(id);
    }
}
