package pl.ujbtrinity.devplatform.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendVerificationToken(String recipient, String token) throws MessagingException;
}
