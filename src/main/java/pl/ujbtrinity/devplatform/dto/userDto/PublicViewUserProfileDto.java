package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PublicViewUserProfileDto {

    private String username;
    private String firstName;
    private Set<String> technologies;
    private Set<String> frameworks;
    private String photoUrl;

    public static PublicViewUserProfileDto fromUser(User user){
        PublicViewUserProfileDto publicUserProfileDto = new PublicViewUserProfileDto();
        publicUserProfileDto.setFirstName(user.getFirstName());
        publicUserProfileDto.setUsername(user.getUsername());
        publicUserProfileDto.setFrameworks(user.getFrameworks()
                .stream().map(Framework::getName)
                .collect(Collectors.toSet()));
        publicUserProfileDto.setTechnologies(user.getTechnologies()
                .stream().map(Technology::getName)
                .collect(Collectors.toSet()));
        publicUserProfileDto.setPhotoUrl("localhost:8081/user/photo/" + user.getId());
        return publicUserProfileDto;
    }
}
