package com.spring.project.Service;

import com.spring.project.Controller.dto.request.AuthRequest;
import com.spring.project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.spring.project.Entity.User;

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
        User user = new User(
                null,
                authRequest.getEmail(),
                authRequest.getUserName(),
                authRequest.getPassword()
        );
        return userRepository.save(user);
    }

//    @Override
//    public User login(AuthRequest authRequest) {
//        User user = new User();
//        return userRepository.find(user);
//    }

}
