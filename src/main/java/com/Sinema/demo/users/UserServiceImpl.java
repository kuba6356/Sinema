package com.Sinema.demo.users;

import com.Sinema.demo.configuration.PasswordEncoder;
import com.Sinema.demo.tokens.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordTokenRepository passwordTokenRepository;
    private final ValidationTokenRepository validationTokenRepository;
    private final JwtService jwtService;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordTokenRepository passwordTokenRepository, ValidationTokenRepository validationTokenRepository, JwtService jwtService, JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordTokenRepository = passwordTokenRepository;
        this.validationTokenRepository = validationTokenRepository;
        this.jwtService = jwtService;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }


    @Override
    @Transactional
    public void registerNewUser(UserDTO user) {
        try{
            User newUser = new User(user.getEmail(), passwordEncoder.getbCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(newUser);
            ValidationToken validationToken = new ValidationToken(newUser);
            validationTokenRepository.save(validationToken);
            sentTokenEmail(newUser,validationToken.getToken(), "verification.html");
        }
        catch (DuplicateKeyException e){
            //TODO Change the exception to a custom one
        }
    }

    @Override
    public String loginUser(UserDTO user) {
        try {
            User foundUser = userRepository.findByEmail(user.getEmail());
            if(foundUser.getId() == null){
                System.out.println("No user found");
                //TODO Create a custom exception
            }

            if(passwordEncoder.getbCryptPasswordEncoder().matches
                    (user.getPassword(), foundUser.getPasswordHash())){
                return jwtService.generateToken(user.getEmail());
            }
            else {
                System.out.println("Wrong password");
                //TODO create custom error handler for wrong password/email
            }
        }
        catch (Exception e){
            //TODO create custom error handler for no password/email in the Body
        }
        return null;
    }

    @Override
    @Transactional
    public void sentResetToken(String email) {
        try{
            System.out.println(email);
            User user = userRepository.findByEmail(email);
            if(user == null){
                System.out.println("No user found");
                //TODO Create a custom exception
            }
            PasswordToken passwordToken = new PasswordToken(user);
                    passwordTokenRepository.save(passwordToken);
                    sentTokenEmail(user, passwordToken.getToken(),"passwordReset.html");
        }catch (Exception e){
            System.out.println(e);
            //TODO Already exists exception that generates new one in place of the previous one
        }
    }

    public void sentTokenEmail(User user, String token, String emailType){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("test");
            Context htmlContent = new Context();
            htmlContent.setVariable("verificationLink", token);
            String html = templateEngine.process("emails/"+emailType, htmlContent);
            helper.setText(html,true);
            javaMailSender.send(message);
        }
            catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void ChangePasswordUsingToken(String token, String newPassword) {
        try {
            PasswordToken passwordToken = passwordTokenRepository.findByToken(token);
            if(passwordToken == null){
                //TODO add custom no token exception
            }
            else if (passwordToken.getExpirationDateTime().isBefore(LocalDateTime.now())) {
                //TODO add custom token timeout
            }

            System.out.println("test");
            User user = passwordToken.getUser();
            user.setPasswordHash(passwordEncoder.getbCryptPasswordEncoder().encode(newPassword));
            System.out.println(user);
            System.out.println(user.getPasswordHash());
            userRepository.save(user);

        }catch (Exception e){
            System.out.println("TEST" + e);
            //TODO add custom exception
        }
    }

    @Override
    @Transactional
    public void validateNewUser(String token) {
        try{
            ValidationToken validationToken = validationTokenRepository.findByToken(token);
            if(validationToken == null){
                //TODO add custom no token exception
            } else if (validationToken.getExpirationDateTime().isBefore(LocalDateTime.now())) {
                //TODO add custom token timeout
            }
            User user = validationToken.getUser();
            user.setRole("User");
            userRepository.save(user);
        }catch (Exception e){
            //TODO add custom exception
        }
    }
}
