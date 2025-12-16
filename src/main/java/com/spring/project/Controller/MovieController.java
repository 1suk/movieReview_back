package com.spring.project.Controller;

import com.spring.project.Controller.dto.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.spring.project.Entity.Movie;
import com.spring.project.Service.MovieService;
import java.util.List;

@Controller
@RequestMapping("/api/movie")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<MovieResponse.MovieListDTO> getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        MovieResponse.MovieListDTO result = MovieResponse.MovieListDTO.of(movies);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
