package pl.ujbtrinity.devplatform.dto.projectDto;

import lombok.Data;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ProjectViewDto {
    private Long id;
    private String name;
    private String description;
    private String creator;
    private Set<String> frameworks;
    private Set<String> technologies;

    public static ProjectViewDto fromProject(Project project) {
        ProjectViewDto projectViewDto = new ProjectViewDto();
        projectViewDto.setId(project.getId());
        projectViewDto.setName(project.getName());
        projectViewDto.setDescription(project.getDescription());
        projectViewDto.setCreator(project.getCreator().getUsername());
        projectViewDto.setFrameworks(project.getFrameworksUsed()
                .stream().map(Framework::getName)
                .collect(Collectors.toSet()));
        projectViewDto.setTechnologies(project.getTechnologiesUsed()
                .stream().map(Technology::getName)
                .collect(Collectors.toSet()));
        return projectViewDto;
    }
}
