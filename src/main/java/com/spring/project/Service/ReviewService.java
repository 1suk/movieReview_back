package com.spring.project.Service;

import com.spring.project.Controller.dto.ReviewDTO;
import com.spring.project.Entity.Review;
import java.util.List;

public interface ReviewService {
    ReviewDTO.Response createReview(ReviewDTO.Create createDto);
    List<ReviewDTO.Response> getReviewsByMovie(Long movieNo);
    ReviewDTO.Response updateReview(Long reviewNo,ReviewDTO.Update updateDto);
    void deleteReview(Long reviewNo);
    List<ReviewDTO.ResponseWithMovie> getReviewsByUser(Long userId);
}