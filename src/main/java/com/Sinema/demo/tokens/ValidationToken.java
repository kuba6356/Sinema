package com.Sinema.demo.tokens;

import com.Sinema.demo.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class ValidationToken extends Token{

    private static final int EXPIRATION_TIME_IN_MINUTES = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id",
                unique = true)
    private User user;
    private LocalDateTime expirationDateTime;
    private String token;

    public ValidationToken(User user) {
        this.user = user;
        this.expirationDateTime = calculateExpirationDateTime(EXPIRATION_TIME_IN_MINUTES);
        this.token = String.valueOf(UUID.randomUUID());
    }

    public ValidationToken() {
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
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String getToken() {
        return super.getToken();
    }

    @Override
    public void setToken(String token) {
        super.setToken(token);
    }

    @Override
    public User getUser() {
        return super.getUser();
    }

    @Override
    public void setUser(User user) {
        super.setUser(user);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
