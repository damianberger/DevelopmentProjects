package pl.ujbtrinity.devplatform.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileEditDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
