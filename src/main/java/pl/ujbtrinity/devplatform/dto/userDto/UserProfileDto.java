package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;
import pl.ujbtrinity.devplatform.entity.Framework;
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
    private String photo;
    private Set<String> technologies;
    private Set<String> frameworks;

    public static UserProfileDto fromUser(User user){
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setFirstName(user.getFirstName());
        userProfileDto.setLastName(user.getLastName());
        userProfileDto.setUsername(user.getUsername());
        userProfileDto.setDescription(user.getDescription());
        userProfileDto.setCity(user.getCity());
        userProfileDto.setPhoto(user.getPhoto());
        userProfileDto.frameworks = user.getFrameworks()
                .stream().map(Framework::getName)
                .collect(Collectors.toSet());
        userProfileDto.technologies = user.getTechnologies()
                .stream().map(Technology::getName)
                .collect(Collectors.toSet());
        return userProfileDto;
    }


}
