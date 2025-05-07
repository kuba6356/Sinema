package com.Sinema.demo.movies;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieService {
    Movie getMovie(String name);

    Movie addNewMovie(Movie newMovie);

    List<Movie> getAllMovies();

    Movie changeMovie(Movie movie, String name);

    void deleteMovie(String name);
}
