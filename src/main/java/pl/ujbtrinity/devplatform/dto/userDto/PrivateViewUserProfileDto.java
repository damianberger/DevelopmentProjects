package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Project;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.entity.User;

import java.util.Set;
import java.util.stream.Collectors;


@Data
public class PrivateViewUserProfileDto {

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

    public static PrivateViewUserProfileDto fromUser(User user) {
        PrivateViewUserProfileDto privateUserProfileDto = new PrivateViewUserProfileDto();
        privateUserProfileDto.setEmail(user.getEmail());
        privateUserProfileDto.setFirstName(user.getFirstName());
        privateUserProfileDto.setLastName(user.getLastName());
        privateUserProfileDto.setUsername(user.getUsername());
        privateUserProfileDto.setDescription(user.getDescription());
        privateUserProfileDto.setCity(user.getCity());
        privateUserProfileDto.setFrameworks(user.getFrameworks()
                .stream().map(Framework::getName)
                .collect(Collectors.toSet()));
        privateUserProfileDto.setTechnologies(user.getTechnologies()
                .stream().map(Technology::getName)
                .collect(Collectors.toSet()));
        privateUserProfileDto.setProjects(user.getProjects()
                .stream().map(Project::getName)
                .collect(Collectors.toSet()));
        privateUserProfileDto.setPhotoUrl("localhost:8081/user/photo/" + user.getId());
        return privateUserProfileDto;
    }


}
