package com.unicap.sin.projetoaos.controller;

import com.unicap.sin.projetoaos.exception.NotFoundException;
import com.unicap.sin.projetoaos.model.Genre;
import com.unicap.sin.projetoaos.model.Movie;
import com.unicap.sin.projetoaos.model.MovieGenre;
import com.unicap.sin.projetoaos.repository.GenreRepository;
import com.unicap.sin.projetoaos.repository.MovieGenreRepository;
import com.unicap.sin.projetoaos.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/moviegenres")
public class MovieGenreController {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieGenreRepository movieGenreRepository;

    @Autowired
    public MovieGenreController(MovieRepository movieRepository, GenreRepository genreRepository, MovieGenreRepository movieGenreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.movieGenreRepository = movieGenreRepository;
    }

    @GetMapping
    public List<MovieGenre> getAllMovieGenres() {
        return movieGenreRepository.findAll();
    }

    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieGenre createMovieGenre(@RequestBody MovieGenre movieGenre) {
        return movieGenreRepository.save(movieGenre);
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieGenre createMovieGenre(@RequestBody Map<String, Long> ids) {
        Long movieId = ids.get("movieId");
        Long genreId = ids.get("genreId");

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Movie not found"));
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException("Genre not found"));

        MovieGenre movieGenre = new MovieGenre();
        movieGenre.setMovie(movie);
        movieGenre.setGenre(genre);

        return movieGenreRepository.save(movieGenre);
    }

    /*@PutMapping("/{id}")
    public MovieGenre updateMovieGenre(@PathVariable Long id, @RequestBody MovieGenre updatedMovieGenre) {
        MovieGenre existingMovieGenre = movieGenreRepository.findById(id).orElseThrow(() -> new NotFoundException("MovieGenre not found"));
        existingMovieGenre.setMovie(updatedMovieGenre.getMovie());
        existingMovieGenre.setGenre(updatedMovieGenre.getGenre());
        return movieGenreRepository.save(existingMovieGenre);
    }*/

    @PutMapping("/{id}")
    public MovieGenre updateMovieGenre(@PathVariable Long id, @RequestBody Map<String, Long> ids) {
        MovieGenre existingMovieGenre = movieGenreRepository.findById(id).orElseThrow(() -> new NotFoundException("MovieGenre not found"));

        Long movieId = ids.get("movieId");
        Long genreId = ids.get("genreId");

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Movie not found"));
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException("Genre not found"));

        existingMovieGenre.setMovie(movie);
        existingMovieGenre.setGenre(genre);

        return movieGenreRepository.save(existingMovieGenre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieGenre(@PathVariable Long id) {
        movieGenreRepository.deleteById(id);
    }
}
