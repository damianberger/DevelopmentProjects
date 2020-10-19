package pl.ujbtrinity.devplatform.dto.projectDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectSearchReceivedDto {
    private String name;
    private String viewAddressRedirect;
    private LocalDate created;
    private Set<String> frameworks;
    private Set<String> technologies;

    public static ProjectSearchReceivedDto fromProject(Project project){
        ProjectSearchReceivedDto projectReceived = new ProjectSearchReceivedDto();
        projectReceived.setName(project.getName());
        projectReceived.setCreated(project.getCreated());
        projectReceived.setViewAddressRedirect("/project/view/" + project.getId());
        projectReceived.setFrameworks(project.getFrameworksUsed()
                .stream().map(Framework::getName)
                .collect(Collectors.toSet()));
        projectReceived.setTechnologies(project.getTechnologiesUsed()
                .stream().map(Technology::getName)
                .collect(Collectors.toSet()));
        return projectReceived;
    }
}
