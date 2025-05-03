package com.Sinema.demo.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String MainPage(){
        return "index";
    }

    @GetMapping("/register")
    public String getRegistrationPage(){
        return "userTemplates/register";
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO user){
        userService.registerNewUser(user);
        return new ResponseEntity("Registration Successful", HttpStatus.OK);
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "userTemplates/login";
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserDTO user){
        userService.loginUser(user);
        return new ResponseEntity("Login Successful", HttpStatus.OK);
    }
}
