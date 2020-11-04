package pl.ujbtrinity.devplatform.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ujbtrinity.devplatform.dto.userDto.AuthenticationDto;
import pl.ujbtrinity.devplatform.security.JwtUtility;

@RestController
public class AuthenticationController {

    private final JwtUtility jwtUtility;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(JwtUtility jwtUtility, AuthenticationManager authenticationManager) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody AuthenticationDto authenticationDto) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(),authenticationDto.getPassword()));
        } catch (Exception e) {
            throw new Exception("invalid login data");
        }
        return jwtUtility.generateToken(authenticationDto.getUsername());
    }


}
