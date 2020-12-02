package pl.ujbtrinity.devplatform.dto.userDto;

import lombok.Data;
import pl.ujbtrinity.devplatform.entity.Role;
import pl.ujbtrinity.devplatform.entity.User;


import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserLoginCredentialsDto {
    private String accessToken;
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;

    public static UserLoginCredentialsDto fromUser(User user) {
        UserLoginCredentialsDto userProfileDto = new UserLoginCredentialsDto();
        userProfileDto.setId(user.getId());
        userProfileDto.setUsername(user.getUsername());
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setRoles(user.getRoles()
                .stream().map(Role::getName)
                .collect(Collectors.toSet()));
        return userProfileDto;
    }
}
