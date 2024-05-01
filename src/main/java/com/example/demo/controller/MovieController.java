package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/create")
    public ResponseEntity<Movie> writeMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping(value = "/single-movie/{movieId}")
    public ResponseEntity<Movie> getSingleMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.getSingleMovie(movieId));
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
       return ResponseEntity.ok(movieService.findAll());
    }

    @PutMapping(value = "/update")
    public ResponseEntity<List<Movie>> updateMovie(@RequestBody Movie movie, @PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.updateMovie(movie, movieId));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<List<Movie>> deleteMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.deleteMovie(movieId));
    }
}
