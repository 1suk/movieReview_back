package com.spring.project.Service;

import java.util.List;
import com.spring.project.Entity.Movie;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
}
