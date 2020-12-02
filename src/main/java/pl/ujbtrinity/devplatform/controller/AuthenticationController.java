package pl.ujbtrinity.devplatform.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pl.ujbtrinity.devplatform.dto.userDto.AuthenticationDto;
import pl.ujbtrinity.devplatform.dto.userDto.UserLoginCredentialsDto;
import pl.ujbtrinity.devplatform.dto.userDto.UserRegistrationDto;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.entity.VerificationToken;
import pl.ujbtrinity.devplatform.security.JwtUtility;
import pl.ujbtrinity.devplatform.service.impl.EmailServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.VerificationTokenServiceImpl;

import javax.mail.MessagingException;

@CrossOrigin
@RestController
public class AuthenticationController {

    private final JwtUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final VerificationTokenServiceImpl verificationTokenService;
    private final EmailServiceImpl emailService;

    public AuthenticationController(JwtUtility jwtUtility, AuthenticationManager authenticationManager, UserServiceImpl userService, VerificationTokenServiceImpl verificationTokenService, EmailServiceImpl emailService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.emailService = emailService;
    }

    @PostMapping("/api/auth/login")
    public UserLoginCredentialsDto authenticate(@RequestBody AuthenticationDto authenticationDto) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(),authenticationDto.getPassword()));
        } catch (Exception e) {
            throw new Exception("invalid login data");
        }
        UserLoginCredentialsDto loginData = userService.getUserCredentials(authenticationDto.getUsername());
        loginData.setAccessToken(jwtUtility.generateToken(authenticationDto.getUsername()));
        return loginData;
    }

    @PostMapping("/api/auth/register")
    public String registration(@RequestBody UserRegistrationDto userRegistrationDto) throws MessagingException {
        this.userService.register(userRegistrationDto);
        VerificationToken verificationToken = new VerificationToken(userService.findByUsername(userRegistrationDto.getUsername()));
        verificationTokenService.saveToken(verificationToken);
        emailService.sendVerificationToken(userRegistrationDto.toUser().getEmail(), verificationToken.getToken());
        return "check your mailbox";
    }

    @GetMapping("api/auth/register/confirm")
    public String confirmRegistration(@RequestParam("token") String verificationToken) {
        VerificationToken token = verificationTokenService.findByToken(verificationToken);
        if (token != null) {
            User userByUsername = userService.findByUsername(token.getUser().getUsername());
            String username = userByUsername.getUsername();
            this.userService.setStatus(username);
        }
        return "user registered";
    }


}
