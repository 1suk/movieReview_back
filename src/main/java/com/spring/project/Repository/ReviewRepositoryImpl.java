package com.spring.project.Repository;

import com.spring.project.Entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.spring.project.Controller.dto.ReviewDTO;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    @Override
    public List<Review> findByMovieWithUser(Long movieNo) {
        String jpql = "SELECT r FROM Review r" +
                    " JOIN FETCH r.user " +
                "WHERE r.movie.movieNo = :movieNo " +
                "ORDER BY r.createdAt DESC";
        return em.createQuery(jpql, Review.class)
                .setParameter("movieNo", movieNo)
                .getResultList();
    }

    @Override
    public List<Review> findReviewsByUserId(Long userId) {
        String jpql = "SELECT r FROM Review r " +
                "JOIN FETCH r.movie m " +
                "JOIN FETCH r.user u " +
                "WHERE u.userId = :userId " +
                "ORDER BY r.rating DESC";

        return em.createQuery(jpql, Review.class)
                .setParameter("userId", userId)
                .getResultList();
    }


    @Override
    public Optional<Review> findById(Long reviewNo) {
        String jpql = "SELECT r FROM Review r " +
                "JOIN FETCH r.user " +
                "JOIN FETCH r.movie " +
                "WHERE r.reviewNo = :reviewNo";

        List<Review> results = em.createQuery(jpql, Review.class)
                .setParameter("reviewNo", reviewNo)
                .getResultList();

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public void delete(Review review) {
        em.remove(review);
    }
}