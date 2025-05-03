package com.Sinema.demo.tokens;


import com.Sinema.demo.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PasswordToken extends Token{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime expirationDateTime;
    private String token;

    public PasswordToken(User user) {
        super(user);
    }

    public PasswordToken() {
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
