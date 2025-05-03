package com.Sinema.demo.tokens;

import com.Sinema.demo.users.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public abstract class Token {

    private static final int EXPIRATION_TIME_IN_MINUTES = 20;

    private Long id;
    private String token;
    private User user;
    private LocalDateTime expirationDateTime;

    protected Token(User user) {
        this.token = String.valueOf(UUID.randomUUID());
        this.user = user;
        this.expirationDateTime = calculateExpirationDateTime(EXPIRATION_TIME_IN_MINUTES);
    }

    protected Token() {
    }

    private LocalDateTime calculateExpirationDateTime(int expirationTimeInMinutes) {
        return LocalDateTime.now().plusMinutes(expirationTimeInMinutes);
    }


    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(id, token1.id) && Objects.equals(token, token1.token) && Objects.equals(user, token1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, user);
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
