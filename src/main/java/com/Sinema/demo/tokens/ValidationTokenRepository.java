package com.Sinema.demo.tokens;

import com.Sinema.demo.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ValidationTokenRepository extends JpaRepository<ValidationToken, Long> {
    ValidationToken findByUser(User newUser);

    ValidationToken findByToken(String token);

    List<ValidationToken> findAllByExpirationDateTimeBefore(LocalDateTime now);
}
