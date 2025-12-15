package com.spring.project.Service;

import com.spring.project.Entity.User;
import com.spring.project.Controller.dto.request.AuthRequest;

public interface UserService {
//    User signUp(User user);
    User signUp(AuthRequest authRequest);
    User login(AuthRequest authRequest);
}
