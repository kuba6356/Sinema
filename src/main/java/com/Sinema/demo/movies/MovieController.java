package com.Sinema.demo.movies;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{name}")
    public String getMoviePage(@PathVariable String name){
        return "moviesTemplate/movie";
    }

    @GetMapping("/api/v1/movie/{name}")
    public ResponseEntity<Movie> getMovieInfo(@PathVariable String name){
        return ResponseEntity.ok().body(movieService.getMovie(name));
    }

    @GetMapping("/api/v1/movie")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok().body(movieService.getAllMovies());
    }

    @PostMapping("/api/v1/admin/movie")
    public ResponseEntity<Movie> newMovie(@RequestBody Movie newMovie){
        return ResponseEntity.ok().body(movieService.addNewMovie(newMovie));
    }

    @PutMapping("/api/v1/movie/admin/{name}")
    public ResponseEntity<Movie> changeMovieInfo(@PathVariable String name, @RequestBody Movie changedMovie){
        return ResponseEntity.ok().body(movieService.changeMovie(changedMovie, name));
    }

    @DeleteMapping("/api/v1/movie/admin/{name}")
    public ResponseEntity deleteMovie(@PathVariable String name){
        movieService.deleteMovie(name);
        return ResponseEntity.ok().body("deleted");
    }
}
