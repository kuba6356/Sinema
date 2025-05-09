package com.Sinema.demo.users;

import java.net.http.HttpRequest;

public interface UserService {
    void registerNewUser(UserDTO user) throws Exception;

    String loginUser(UserDTO user);

    void sentResetToken(String email);

    void ChangePasswordUsingToken(String token, String newPassword);

    void validateNewUser(String token);

    String deleteUser(Long id);
}
