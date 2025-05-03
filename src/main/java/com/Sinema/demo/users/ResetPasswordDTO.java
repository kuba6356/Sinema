package com.Sinema.demo.users;

import java.util.Objects;

public class ResetPasswordDTO {
    private String email;

    public ResetPasswordDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResetPasswordDTO that = (ResetPasswordDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "ResetPasswordDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
