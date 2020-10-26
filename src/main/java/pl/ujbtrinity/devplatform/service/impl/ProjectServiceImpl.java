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

//    @Override
//    public List<ProjectSearchReceivedDto> projectSearch(ProjectSearchRequestedDto projectSearchRequestedDto) {
//        List<Project> projects = projectRepository.findAll();
//        List<ProjectSearchReceivedDto> projectsFound = new ArrayList<>();
//        for (Project project: projects) {
//        ProjectSearchReceivedDto projectReceived = ProjectSearchReceivedDto.fromProject(project);
//        projectsFound.add(projectReceived);
//        }
//        return projectsFound.stream()
//                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getName().toLowerCase().contains(projectSearchRequestedDto.getName().toLowerCase()))
//                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getCreated().isAfter(projectSearchRequestedDto.getCreatedAfter()))
//                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getCreated().isBefore(projectSearchRequestedDto.getCreatedBefore()))
//                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getTechnologies().containsAll(projectSearchRequestedDto.getTechnologies()))
//                .filter(projectSearchReceivedDto -> projectSearchReceivedDto.getFrameworks().containsAll(projectSearchRequestedDto.getFrameworks()))
//                .collect(Collectors.toList());
//    }

    @Override
    public List<ProjectViewDto> projectSearch(ProjectSearchRequestedDto projectSearchRequestedDto) {
        List<ProjectViewDto> resultList = new ArrayList<>();
        List<Project> allByTechnologiesUsedAndFrameworksUsed =
                projectRepository.findAllByTechnologiesUsedAndFrameworksUsed(projectSearchRequestedDto.getTechnologies(),
                        projectSearchRequestedDto.getFrameworks());
        for (Project project : allByTechnologiesUsedAndFrameworksUsed) {
            resultList.add(ProjectViewDto.fromProject(project));
        }
        return resultList;
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
    }

    @Override
    public void removeUsersFromProject(Long id) {
        userRepository.removeUsersFromProject(id);
    }
}
