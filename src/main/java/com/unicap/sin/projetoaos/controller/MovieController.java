package com.unicap.sin.projetoaos.controller;

import com.unicap.sin.projetoaos.exception.NotFoundException;
import com.unicap.sin.projetoaos.model.Movie;
import com.unicap.sin.projetoaos.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie not found"));
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setSynopsis(updatedMovie.getSynopsis());
        existingMovie.setPosterPath(updatedMovie.getPosterPath());
        return movieRepository.save(existingMovie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
    }
}
