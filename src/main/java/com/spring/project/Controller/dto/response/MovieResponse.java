package com.spring.project.Controller.dto.response;

import lombok.*;
import com.spring.project.Entity.Movie;
import java.util.List;
import java.util.stream.Collectors;


public class MovieResponse {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MovieDTO{
        private Long movieNo;
        private String title;
        private String titleKo;
        private Integer year;
        private String genre;
        private Double rating;
        private String posterUrl;
        private String director;
        private String cast;
        private String synopsis;
        public static MovieDTO of(Movie movie){
            return MovieDTO.builder()
                    .movieNo(movie.getMovieNo())
                    .title(movie.getTitle())
                    .titleKo(movie.getTitleKo())
                    .year(movie.getYear())
                    .genre(movie.getGenre())
                    .rating(movie.getRating())
                    .posterUrl(movie.getPosterUrl())
                    .director(movie.getDirector())
                    .cast(movie.getCast())
                    .synopsis(movie.getSynopsis())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MovieListDTO {
        private List<MovieDTO> movies;
        private int totalCount;

        public static MovieListDTO of(List<Movie> movieList) {
            List<MovieDTO> movieDTOs = movieList.stream()
                    .map(MovieDTO::of)
                    .collect(Collectors.toList());

            return MovieListDTO.builder()
                    .movies(movieDTOs)
                    .totalCount(movieDTOs.size())
                    .build();
        }
    }
}
