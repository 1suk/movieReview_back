package com.spring.project.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.spring.project.Service.UserService;
import com.spring.project.Entity.User;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User singUp(@RequestBody User user){
        System.out.println("SignUp 요청: " + user.getEmail());
        return userService.signUp(user);
    }
}
