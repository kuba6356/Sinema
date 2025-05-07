package com.Sinema.demo.configuration;

import com.Sinema.demo.tokens.PasswordTokenRepository;
import com.Sinema.demo.tokens.ValidationToken;
import com.Sinema.demo.tokens.ValidationTokenRepository;
import com.Sinema.demo.users.User;
import com.Sinema.demo.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CleanupService {

    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final ValidationTokenRepository validationTokenRepository;

    public CleanupService(UserRepository userRepository, PasswordTokenRepository passwordTokenRepository, ValidationTokenRepository validationTokenRepository) {
        this.userRepository = userRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.validationTokenRepository = validationTokenRepository;
    }

    @Transactional
    @Scheduled(fixedRate = 20 * 60 * 1000)
    public void cleanupExpiredTokensAndUnverifiedUsers(){
        passwordTokenRepository.deleteAllByExpirationDateTimeBefore(LocalDateTime.now());
        List<ValidationToken> expiredTokens = validationTokenRepository
                .findAllByExpirationDateTimeBefore(LocalDateTime.now());
        for(ValidationToken token : expiredTokens){
            User user = token.getUser();
            validationTokenRepository.delete(token);
            if(user.getRole().equals("WaitingValidation")){
                userRepository.delete(user);
            }
        }
    }
}
