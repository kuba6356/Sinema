package com.Sinema.demo.users;

import com.Sinema.demo.configuration.PasswordEncoder;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void registerNewUser(UserDTO user) {
        try{
            userRepository.save(new User(user.getEmail(), passwordEncoder.getbCryptPasswordEncoder().encode(user.getPassword())));
        }
        catch (DuplicateKeyException e){
            //TODO Change the exception to a custom one
        }
    }

    @Override
    @Transactional
    public void loginUser(UserDTO user) {
        try {
            User foundUser = userRepository.findByEmail(user.getEmail());
            if(passwordEncoder.getbCryptPasswordEncoder().matches
                    (foundUser.getPasswordHash(), user.getPassword())
                    && !user.getPassword().isEmpty()
                    && !foundUser.getPasswordHash().isEmpty()){
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
}
