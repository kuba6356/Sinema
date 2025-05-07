package com.Sinema.demo.movies;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie getMovie(String name) {
        Movie movie = movieRepository.findByName(name);
        if(movie == null){
            //TODO custom exception
        }
        return movie;
    }

    @Override
    @Transactional
    public Movie addNewMovie(Movie newMovie) {
        movieRepository.save(newMovie);
        return newMovie;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        if(movies == null){
            //TODO Custom exception
        }
        return movies;
    }

    @Override
    @Transactional
    public Movie changeMovie(Movie changedMovie, String name) {
        Movie movie = movieRepository.findByName(name);
        if(movie == null){
            //TODO custom exception
        }
        movie.setCost(changedMovie.getCost());
        movie.setDescription(changedMovie.getDescription());
        movie.setName(changedMovie.getName());
        movie.setThemes(changedMovie.getThemes());
        movie.setCoverUrl(changedMovie.getCoverUrl());
        movieRepository.save(movie);
        return movie;
    }

    @Override
    @Transactional
    public void deleteMovie(String name) {
        Movie movie = movieRepository.findByName(name);
        if(movie == null){
            //TODO custom exception
        }
        movieRepository.delete(movie);
    }
}
