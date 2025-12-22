package com.spring.project.Service;

import com.spring.project.Controller.dto.ReviewDTO;
import com.spring.project.Entity.Review;
import com.spring.project.Entity.Movie;
import com.spring.project.Entity.User;
import com.spring.project.Exception.MovieNotFoundException;
import com.spring.project.Exception.UserNotFoundException;
import com.spring.project.Repository.MovieRepository;
import com.spring.project.Repository.ReviewRepository;
import com.spring.project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

//    @Override
//    public Review createReview(ReviewRequest reviewRequest){
//        Long userId = reviewRequest.getUserId();
//        Long movieNo = reviewRequest.getMovieNo();
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(()-> new UserNotFoundException(userId));
//        Movie movie = movieRepository.findById(movieNo)
//                .orElseThrow(() -> new MovieNotFoundException(movieNo));
//
//        Review review = Review.builder()
//                .user(user)
//                .movie(movie)
//                .rating(reviewRequest.getRating())
//                .content(reviewRequest.getContent())
//                .createdAt(LocalDate.now())
//                .build();
//
//        return reviewRepository.save(review);
//    }
    @Override
    public ReviewDTO.Response createReview(ReviewDTO.Create createDto){
        User user = userRepository.findById(createDto.getUserId())
                .orElseThrow(()-> new UserNotFoundException(createDto.getUserId()));
        Movie movie = movieRepository.findById(createDto.getMovieNo())
                .orElseThrow(() -> new MovieNotFoundException(createDto.getMovieNo()));
        Review review = createDto.toEntity(user,movie);
        Review saved = reviewRepository.save(review);
        return ReviewDTO.Response.of(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO.Response> getReviewsByMovie(Long movieNo) {
        List<Review> reviews = reviewRepository.findByMovieWithUser(movieNo);

        List<ReviewDTO.Response> response = reviews.stream()
                .map(ReviewDTO.Response::of)
                .toList();

        return response;
    }

    @Override
    @Transactional
    public ReviewDTO.Response updateReview(Long reviewNo, ReviewDTO.Update updateDto) {
        Review review = reviewRepository.findById(reviewNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

        review.putUpdate(updateDto.getRating(), updateDto.getContent());

        ReviewDTO.Response response = ReviewDTO.Response.of(
                review.getReviewNo(),
                review.getUser().getUserId(),
                review.getMovie().getMovieNo(),
                review.getUser().getUserName(),
                review.getMovie().getTitleKo(),
                review.getRating(),
                review.getContent(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );

        return response;
    }

    @Override
    @Transactional(readOnly = false)

    public void deleteReview(Long reviewNo) {
        Review review = reviewRepository.findById(reviewNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));
        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO.ResponseWithMovie> getReviewsByUser(Long userId) {
        List<Review> reviews = reviewRepository.findReviewsByUserId(userId);

        return reviews.stream()
                .map(ReviewDTO.ResponseWithMovie::of)
                .toList();
    }
}

