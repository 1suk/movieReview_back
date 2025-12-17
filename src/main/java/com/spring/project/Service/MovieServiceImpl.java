package com.spring.project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.spring.project.Repository.MovieRepository;
import com.spring.project.Entity.Movie;
import com.spring.project.Exception.MovieNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
//
//    @Override
//    public Movie getMovieById(Long id){ return movieRepository.findByMovieNo(id)
//            .orElseThrow(()-> new MovieNotFoundException(id));
//    }
    @Override
    public Movie getMovieById(Long id){ return movieRepository.findById(id)
              .orElseThrow(()-> new MovieNotFoundException(id));
}
}
