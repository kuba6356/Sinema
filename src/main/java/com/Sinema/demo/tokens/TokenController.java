package com.Sinema.demo.tokens;

import com.Sinema.demo.users.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TokenController {

    private final UserServiceImpl userService;

    public TokenController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/resetPassword")
    public String getResetPasswordPage(){
        return "tokenTemplates/resetPassword";
    }

    @PostMapping("/api/v1/resetPassword")
    public ResponseEntity sendResetPasswordToken(@RequestBody String email){
        userService.sentResetToken(email);
        return ResponseEntity.ok().body("reset token sent to email");
    }

    @GetMapping("/reset")
    public String getResetPasswordFormPage(){
        return "tokenTemplates/resetPasswordFormPage";
    }

    @PutMapping("/reset")
    public ResponseEntity changePasswordUsingToken(@RequestParam("resetPasswordToken") String token, @RequestBody String newPassword){
        userService.ChangePasswordUsingToken(token, newPassword);
        return ResponseEntity.ok().body("password changed");
    }

    @GetMapping("/validate")
    public String  validateNewUserEmail(@RequestParam("validationToken") String token){
        userService.validateNewUser(token);
        return "tokenTemplates/validationComplete";
    }
}
