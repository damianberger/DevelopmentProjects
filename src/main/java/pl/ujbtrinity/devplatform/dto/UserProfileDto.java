package pl.ujbtrinity.devplatform.dto;

import lombok.Data;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.entity.User;

import java.util.Set;


@Data
public class UserProfileDto {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Set<Technology> technologies;
    private Set<Framework> frameworks;

    public static UserProfileDto fromUser(User user){
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setFirstName(user.getFirstName());
        userProfileDto.setLastName(user.getLastName());
        userProfileDto.setUsername(user.getUsername());
        userProfileDto.setTechnologies(user.getTechnologies());
        userProfileDto.setFrameworks(user.getFrameworks());
        return userProfileDto;
    }

}
