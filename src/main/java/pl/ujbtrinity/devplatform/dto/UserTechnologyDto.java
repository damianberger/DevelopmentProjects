package pl.ujbtrinity.devplatform.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserTechnologyDto {
    private Set<String> technologies;
}
