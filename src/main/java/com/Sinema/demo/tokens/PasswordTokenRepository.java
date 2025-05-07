package com.Sinema.demo.tokens;

import com.Sinema.demo.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {
    String findByUser(User user);

    PasswordToken findByToken(String token);

    void deleteAllByExpirationDateTimeBefore(LocalDateTime time);
}
