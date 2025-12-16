package com.spring.project.Controller.dto.response;

import lombok.*;
import com.spring.project.Entity.User;

public class AuthResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignUpDTO {
        private Long userId;
        private String email;
        private String userName;

        public static SignUpDTO of(User user) {
            return SignUpDTO.builder()
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
    public static class LoginDTO {
        private Long userId;
        private String email;
        private String userName;

        public static LoginDTO of(User user) {
            return LoginDTO.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .userName(user.getUserName())
                    .build();
        }
    }
}

