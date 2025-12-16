package com.spring.project.Controller;

import com.spring.project.Controller.dto.request.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.spring.project.Service.UserService;
import com.spring.project.Entity.User;

import com.spring.project.Controller.dto.response.AuthResponse;
import com.spring.project.Controller.dto.request.AuthRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse.SignUpDTO> signUp(@RequestBody AuthRequest authRequest){
        System.out.println("SignUp 요청: " + authRequest.getEmail());
        User user = userService.signUp(authRequest);
        AuthResponse.SignUpDTO result = AuthResponse.SignUpDTO.of(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse.LoginDTO> login(@RequestBody AuthRequest authRequest){
        System.out.println("login 요청: " + authRequest.getEmail());
        User user = userService.login(authRequest);
        AuthResponse.LoginDTO result = AuthResponse.LoginDTO.of(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
