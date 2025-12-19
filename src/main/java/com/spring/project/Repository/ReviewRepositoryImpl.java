package com.spring.project.Repository;

import com.spring.project.Entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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
    public Optional<Review> findById(Long reviewNo) {
        String jpql = "SELECT r FROM Review r " +
                "JOIN FETCH r.user " +
                "JOIN FETCH r.movie " +
                "WHERE r.reviewNo = :reviewNo";

        List<Review> results = em.createQuery(jpql, Review.class)
                .setParameter("reviewNo", reviewNo)
                .getResultList();

        return em.createQuery(jpql, Review.class)
                .setParameter("reviewNo", reviewNo)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void delete(Review review) {
        em.remove(review);
    }
}