package com.spring.project.Service;

import com.spring.project.Controller.dto.request.AuthRequest;
import com.spring.project.Repository.UserRepository;
import com.spring.project.Entity.User;
import com.spring.project.Exception.UserNotFoundException;
import com.spring.project.Exception.InvalidPasswordException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

//    @Override
//    public User signUp(User user) {
//        return userRepository.save(user);
//    }

    @Override
    public User signUp(AuthRequest authRequest) {
        User user = User.builder()
                .email(authRequest.getEmail())
                .userName(authRequest.getUserName())
                .password(authRequest.getPassword())
                // reviews는 @Builder.Default로 빈 리스트 자동 설정
                .build();

        return userRepository.save(user);
    }

    @Override
    public User login (AuthRequest authRequest) {
        String requestEmail = authRequest.getEmail();
        System.out.println("이메일: " + requestEmail);
        User user = userRepository.findByEmail(requestEmail)
                .orElseThrow(() -> new UserNotFoundException(requestEmail));
        if(!authRequest.getPassword().equals(user.getPassword())) {
            throw new InvalidPasswordException();
        }
        return user;
    }

}
