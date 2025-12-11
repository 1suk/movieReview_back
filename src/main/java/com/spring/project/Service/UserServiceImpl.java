package com.spring.project.Service;

import com.spring.project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.spring.project.Entity.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User signUp(User user) {
        return userRepository.save(user);
    }
}
