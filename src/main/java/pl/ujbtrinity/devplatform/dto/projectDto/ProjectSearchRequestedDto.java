package pl.ujbtrinity.devplatform.dto.projectDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectSearchRequestedDto {
    private String name;
    private LocalDate createdAfter;
    private LocalDate createdBefore;
    private String frameworks;
    private String technologies;



}
