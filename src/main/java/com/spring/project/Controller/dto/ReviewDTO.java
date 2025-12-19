package com.spring.project.Controller.dto;

import lombok.*;
import com.spring.project.Entity.Movie;
import com.spring.project.Entity.User;
import com.spring.project.Entity.Review;

import java.time.LocalDate;

public class ReviewDTO {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        private Long userId;
        private Long movieNo;
        private Double rating;
        private String content;
        public Review toEntity(User user, Movie movie){
            return Review.builder()
                    .user(user)
                    .movie(movie)
                    .rating(rating) //매개변수에 raing이 있다면 this로 명시
                    .content(content)
                    .createdAt(LocalDate.now())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        private Double rating;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long reviewNo;
        private Long userId;
        private Long movieNo;
        private String userName;
        private String movieTitle;
        private Double rating;
        private String content;
        private LocalDate createdAt;
        private LocalDate updatedAt;
        public static Response of(Long reviewNo, Long userId, Long movieNo,
                                  String userName, String movieTitle, Double rating,
                                  String content, LocalDate createdAt, LocalDate updatedAt){
            return Response.builder()
                    .reviewNo(reviewNo)
                    .userId(userId)
                    .movieNo(movieNo)
                    .userName(userName)
                    .movieTitle(movieTitle)
                    .rating(rating)
                    .content(content)
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .build();
        }

        public static Response of(Review review) {
            return Response.builder()
                    .reviewNo(review.getReviewNo())
                    .userId(review.getUser().getUserId())
                    .movieNo(review.getMovie().getMovieNo())
                    .userName(review.getUser().getUserName())
                    .movieTitle(review.getMovie().getTitleKo())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .updatedAt(review.getUpdatedAt())
                    .build();
        }

        //review받기
        public static Response ofSimple(Review review) {
            return Response.builder()
                    .reviewNo(review.getReviewNo())
                    .userName(review.getUser().getUserName())
                    .rating(review.getRating())
                    .createdAt(review.getCreatedAt())
                    .build();
        }
    }
}
