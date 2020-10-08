package pl.ujbtrinity.devplatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private String firstName;
    private String lastName;

}
