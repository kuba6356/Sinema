package com.Sinema.demo.users;

import java.net.http.HttpRequest;

public interface UserService {
    void registerNewUser(UserDTO user) throws Exception;

    String loginUser(UserDTO user);

    void sentResetToken(ResetPasswordDTO email);
}
