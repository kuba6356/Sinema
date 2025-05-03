package com.Sinema.demo.tokens;


import com.Sinema.demo.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PasswordToken extends Token{

    private static final int EXPIRATION_TIME_IN_MINUTES = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id",
                unique = true)
    private User user;
    private LocalDateTime expirationDateTime;
    private String token;


    public PasswordToken(User user) {
        this.user = user;
        this.expirationDateTime = calculateExpirationDateTime(EXPIRATION_TIME_IN_MINUTES);
        this.token = String.valueOf(UUID.randomUUID());
    }

    public PasswordToken() {
        super();
    }

    @Override
    protected LocalDateTime calculateExpirationDateTime(int expirationTimeInMinutes) {
        return super.calculateExpirationDateTime(expirationTimeInMinutes);
    }

    @Override
    public LocalDateTime getExpirationDateTime() {
        return super.getExpirationDateTime();
    }

    @Override
    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        super.setExpirationDateTime(expirationDateTime);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
