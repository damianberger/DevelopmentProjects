package pl.ujbtrinity.devplatform.dto.projectDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.User;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectCreateDto {
    private String name;
    private String description;
    private String creator;
    private Set<String> frameworks;
    private Set<String> technologies;

    public Project toProject(){
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        return project;
    }
}
