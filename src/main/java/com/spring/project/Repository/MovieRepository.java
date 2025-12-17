package com.spring.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.project.Entity.Movie;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    //findAll()
    //Optional<Movie> findByMovieNo(Long movieNo);
    //findById()
}
