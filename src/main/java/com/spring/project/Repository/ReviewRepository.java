package com.spring.project.Repository;

import com.spring.project.Controller.dto.ReviewDTO;
import com.spring.project.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);
    List<Review> findByMovieWithUser(Long movieNo);
    Optional<Review> findById(Long reviewNo);
    void delete(Review review);
}
