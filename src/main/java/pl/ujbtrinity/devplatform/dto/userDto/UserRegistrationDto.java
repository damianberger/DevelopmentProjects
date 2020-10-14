package pl.ujbtrinity.devplatform.dto.userDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pl.ujbtrinity.devplatform.entity.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegistrationDto {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordConfirmation;
    private String role;

    public User toUser(){
    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setPassword(password);
    return user;
    }

}
