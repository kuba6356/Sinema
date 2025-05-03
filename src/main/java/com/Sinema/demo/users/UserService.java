package com.Sinema.demo.users;

public interface UserService {
    void registerNewUser(UserDTO user) throws Exception;

    void loginUser(UserDTO user);
}
