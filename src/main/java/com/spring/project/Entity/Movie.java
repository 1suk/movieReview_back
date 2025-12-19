package com.spring.project.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIE")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_NO")
    private Long movieNo;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String titleKo;

    @Column(name = "`year`", nullable = false)
    private Integer year;

    @Column(length = 200, nullable = false)
    private String genre;

    @Column(nullable = false)
    private Double rating;

    @Column(length = 500, nullable = false)
    private String posterUrl;

    @Column(length = 100, nullable = false)
    private String director;

    @Column(name = "`cast`", length = 500, nullable = false)
    private String cast;

    @Column(length = 2000)
    private String synopsis;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}