package com.Sinema.demo.tokens;

import com.Sinema.demo.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ValidationToken extends Token{

    private static final int EXPIRATION_TIME_IN_MINUTES = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "validationToken_seq")
    @SequenceGenerator(name = "validationToken_seq", sequenceName = "validationToken_sequence", allocationSize = 1)
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
    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    @Override
    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ValidationToken that = (ValidationToken) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(expirationDateTime, that.expirationDateTime) && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, user, expirationDateTime, token);
    }

    @Override
    public String toString() {
        return "ValidationToken{" +
                "id=" + id +
                ", user=" + user +
                ", expirationDateTime=" + expirationDateTime +
                ", token='" + token + '\'' +
                '}';
    }
}
