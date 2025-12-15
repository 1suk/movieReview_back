package com.spring.project.Controller.dto.response;

import lombok.*;
import com.spring.project.Entity.User;

public class AuthResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignUp {
        private Long userId;
        private String email;
        private String userName;
        private String message;

        public static SignUp of(User user) {
            return SignUp.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .userName(user.getUserName())
                    .build();
            }
        }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Login {
        private Long userId;
        private String email;
        private String userName;
        private String message;

        public static Login of(User user) {
            return Login.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .userName(user.getUserName())
                    .build();
        }
    }
}

