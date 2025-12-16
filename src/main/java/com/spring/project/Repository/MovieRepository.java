package com.spring.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.project.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    //findAll()
}
