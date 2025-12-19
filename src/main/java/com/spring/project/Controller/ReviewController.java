package com.spring.project.Controller;

import com.spring.project.Controller.dto.ReviewDTO;
import com.spring.project.Entity.Review;
import com.spring.project.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {
    private final ReviewService reviewService;

//    @PostMapping
//    public ResponseEntity<ReviewResponse.ReviewDTO> createReview(@RequestBody ReviewRequest reviewRequest) {
//        System.out.println("요청: " + reviewRequest);
//        Review review = reviewService.createReview(reviewRequest);
//        ReviewResponse.ReviewDTO result = ReviewResponse.ReviewDTO.of(review);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

        @PostMapping
        public ResponseEntity<ReviewDTO.Response> createReview(@RequestBody ReviewDTO.Create createDto){
            Review review = reviewService.createReview(createDto);
            ReviewDTO.Response response = ReviewDTO.Response.of(review);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/movie/{movieNo}")
        public ResponseEntity<List<ReviewDTO.Response>> getReviewsByMovie(@PathVariable Long movieNo) {
            List<ReviewDTO.Response> response = reviewService.getReviewsByMovie(movieNo);
            return ResponseEntity.ok(response);
        }

        @PutMapping("/{reviewNo}")
        public ResponseEntity<ReviewDTO.Response> updateReview(@RequestBody ReviewDTO.Update updateDto, @PathVariable Long reviewNo ){
            ReviewDTO.Response response = reviewService.updateReview(reviewNo,updateDto);
            return ResponseEntity.ok(response);
        }

}