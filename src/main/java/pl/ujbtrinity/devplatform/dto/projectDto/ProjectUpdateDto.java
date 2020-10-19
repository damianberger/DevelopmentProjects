package pl.ujbtrinity.devplatform.dto.projectDto;

import lombok.Data;

import java.util.Set;

@Data
public class ProjectUpdateDto {
    private Long id;
    private String description;
    private Set<String> frameworksUsed;
    private Set<String> technologiesUsed;
}
