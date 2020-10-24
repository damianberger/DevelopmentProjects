package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;
import pl.ujbtrinity.devplatform.entity.User;

@Data
public class UserProfileChangeDto {

    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String city;

    public static UserProfileChangeDto fromUser(User user){
        UserProfileChangeDto userProfileChangeDto = new UserProfileChangeDto();
        userProfileChangeDto.setFirstName(user.getFirstName());
        userProfileChangeDto.setLastName(user.getLastName());
        userProfileChangeDto.setUsername(user.getUsername());
        userProfileChangeDto.setDescription(user.getDescription());
        userProfileChangeDto.setCity(user.getCity());
        return userProfileChangeDto;
    }


}
