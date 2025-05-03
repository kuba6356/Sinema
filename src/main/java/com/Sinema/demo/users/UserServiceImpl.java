package com.Sinema.demo.users;

import com.Sinema.demo.configuration.PasswordEncoder;
import com.Sinema.demo.tokens.PasswordToken;
import com.Sinema.demo.tokens.PasswordTokenRepository;
import com.Sinema.demo.tokens.ValidationToken;
import com.Sinema.demo.tokens.ValidationTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordTokenRepository passwordTokenRepository;
    private final ValidationTokenRepository validationTokenRepository;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordTokenRepository passwordTokenRepository, ValidationTokenRepository validationTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordTokenRepository = passwordTokenRepository;
        this.validationTokenRepository = validationTokenRepository;
    }


    @Override
    @Transactional
    public void registerNewUser(UserDTO user) {
        try{
            User newUser = new User(user.getEmail(), passwordEncoder.getbCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(newUser);
            ValidationToken validationToken = new ValidationToken(newUser);
            validationTokenRepository.save(validationToken);
            //TODO Send email with validation token
        }
        catch (DuplicateKeyException e){
            //TODO Change the exception to a custom one
        }
    }

    @Override
    public void loginUser(UserDTO user) {
        try {
            User foundUser = userRepository.findByEmail(user.getEmail());
            if(foundUser.getId() == null){
                System.out.println("No user found");
                //TODO Create a custom exception
            }
            if(passwordEncoder.getbCryptPasswordEncoder().matches
                    (foundUser.getPasswordHash(), user.getPassword())){
                //TODO create new JWT token and save it
            }
            else {
                System.out.println("Wrong password");
                //TODO create custom error handler for wrong password/email
            }
        }
        catch (Exception e){
            //TODO create custom error handler for no password/email in the Body
        }
    }

    @Override
    @Transactional
    public void sentResetToken(ResetPasswordDTO email) {
        try{
            User user = userRepository.findByEmail(email.getEmail());
            if(user.getId() == null){
                System.out.println("No user found");
                //TODO Create a custom exception
            }
            PasswordToken passwordToken = new PasswordToken(user);
                    passwordTokenRepository.save(passwordToken);
            //TODO sent email notification with thymeleaf
            System.out.println("Token generated: localhost:8080/api/v1/resetPassword?resetToken="+
                                passwordToken.getToken());
        }catch (Exception e){
            System.out.println("Token already exists");
            //TODO Already exists exception that generates new one in place of the previous one
        }
    }


}
