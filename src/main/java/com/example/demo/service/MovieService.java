package com.example.demo.service;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getSingleMovie(Long movieId) {
        return movieRepository.findById(movieId).get();
    }

    public List<Movie> updateMovie(Movie movie, Long movieId) {
        Movie existingMovie = movieRepository.findById(movieId).get();

        if (existingMovie.getId() != null) {
            existingMovie.setDirector(movie.getDirector());
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setReleaseDate(movie.getReleaseDate());
            movieRepository.save(existingMovie);
        }
        return movieRepository.findAll();
    }

    public List<Movie> deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
        return movieRepository.findAll();
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
