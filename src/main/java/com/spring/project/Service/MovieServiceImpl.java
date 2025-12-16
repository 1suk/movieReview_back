package com.spring.project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.spring.project.Repository.MovieRepository;
import com.spring.project.Entity.Movie;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
}
