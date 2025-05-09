package com.Sinema.demo.users;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

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

    @PostMapping("/api/v1/register")
    public ResponseEntity register(@RequestBody UserDTO user){
        userService.registerNewUser(user);
        return new ResponseEntity("Registration Successful", HttpStatus.OK);
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "userTemplates/login";
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity login(@RequestBody UserDTO user, HttpServletResponse response){
        response.setHeader("Authorization","Bearer " +userService.loginUser(user));
        return new ResponseEntity("Login Successful", HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/admin/user/delete{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }


}
