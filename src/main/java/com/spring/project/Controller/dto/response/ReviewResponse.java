package com.spring.project.Controller.dto.response;

import lombok.*;

import com.spring.project.Entity.Review;

public class ReviewResponse {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReviewDTO {
        private Long reviewNo;
        private Long userId;
        private Long movieNo;
        private String userName;
        private String movieTitle;
        private Double rating;
        private String content;

        public static ReviewDTO of(Review review){
            return ReviewDTO.builder()
                    .reviewNo(review.getReviewNo())
                    .userId(review.getUser().getUserId())
                    .userName(review.getUser().getUserName())
                    .movieNo(review.getMovie().getMovieNo())
                    .movieTitle(review.getMovie().getTitle())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .build();
        }
    }
}
