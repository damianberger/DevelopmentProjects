package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.entity.User;

import java.util.Set;
import java.util.stream.Collectors;


@Data
public class UserProfileDto {


    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String description;
    private String city;
    private Set<String> technologies;
    private Set<String> frameworks;
    private Set<String> projects;
    private String photoUrl;

    public static UserProfileDto fromUser(User user) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setFirstName(user.getFirstName());
        userProfileDto.setLastName(user.getLastName());
        userProfileDto.setUsername(user.getUsername());
        userProfileDto.setDescription(user.getDescription());
        userProfileDto.setCity(user.getCity());
        userProfileDto.setFrameworks(user.getFrameworks()
                .stream().map(Framework::getName)
                .collect(Collectors.toSet()));
        userProfileDto.setTechnologies(user.getTechnologies()
                .stream().map(Technology::getName)
                .collect(Collectors.toSet()));
        userProfileDto.setProjects(user.getProjects()
                .stream().map(Project::getName)
                .collect(Collectors.toSet()));
        userProfileDto.setPhotoUrl("localhost:8081/user/photo/" + user.getId());
        return userProfileDto;
    }


}
