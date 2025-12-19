package com.spring.project.Controller.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
public class ReviewRequest {
    private Long userId;
    private Long movieNo;
    private Double rating;
    private String content;
}
