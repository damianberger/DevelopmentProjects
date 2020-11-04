package pl.ujbtrinity.devplatform.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ujbtrinity.devplatform.dto.userDto.UserRegistrationDto;
import org.springframework.web.bind.annotation.*;
import pl.ujbtrinity.devplatform.entity.User;
import pl.ujbtrinity.devplatform.entity.VerificationToken;
import pl.ujbtrinity.devplatform.service.impl.EmailServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.VerificationTokenServiceImpl;

import javax.mail.MessagingException;

@RestController
public class RegistrationController {

    private final UserServiceImpl userService;
    private final VerificationTokenServiceImpl verificationTokenService;
    private final EmailServiceImpl emailService;

    public RegistrationController(UserServiceImpl userService, VerificationTokenServiceImpl verificationTokenService, EmailServiceImpl emailService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.emailService = emailService;
    }


    @PostMapping("/register")
    public String registration(@RequestBody UserRegistrationDto userRegistrationDto) throws MessagingException {
        this.userService.register(userRegistrationDto);
        VerificationToken verificationToken = new VerificationToken(userService.findByUsername(userRegistrationDto.getUsername()));
        verificationTokenService.saveToken(verificationToken);
        emailService.sendVerificationToken(userRegistrationDto.toUser().getEmail(), verificationToken.getToken());
        return "check your mailbox";
    }

    @GetMapping("/register/confirm")
    public String confirmRegistration(@RequestParam ("token") String verificationToken) {
        VerificationToken token = verificationTokenService.findByToken(verificationToken);
        if (token != null) {
            User userByUsername = userService.findByUsername(token.getUser().getUsername());
            String username = userByUsername.getUsername();
            this.userService.setStatus(username);
        }
        return "user registered";
    }
}
