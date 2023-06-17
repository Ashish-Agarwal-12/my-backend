package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Movies;
import com.multiplex.ticketBooking.exception.MovieNotCreatedException;
import com.multiplex.ticketBooking.exception.MovieNotFoundException;
import com.multiplex.ticketBooking.service.MoviesService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoviesController {

    public static final Logger logger = LoggerFactory.getLogger(MoviesController.class);

    @Autowired
    private MoviesService moviesService;

    @PostMapping("/addMovie")
    public Movies addMovie(@Valid @RequestBody Movies movie) throws MovieNotCreatedException {
        logger.info("Adding movie");
        Movies createMovie = moviesService.addMovie(movie);
        if(createMovie == null){
            throw new MovieNotCreatedException("This Movie could not be created");
        }
        return createMovie;
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movies>> getAllMovies(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        logger.info("Getting all movie");
        List<Movies> moviesList =  moviesService.getAllMovies(pageNumber, pageSize);
        return new ResponseEntity<List<Movies>>(moviesList, HttpStatus.OK);
    }

    @GetMapping("/getMovieById/{id}")
    public Movies getMovieById(@PathVariable Long id) {

        logger.info("Getting movie by id");
        return moviesService.getMovieById(id);
    }

    @PutMapping("/updateMovie/{id}")
    public Movies updateMovie(@Valid @RequestBody Movies movie, @PathVariable Long id){

        logger.info("Updating movie");
        return moviesService.updateMovie(movie, id);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public void deleteMovie(@PathVariable Long id) {

        logger.info("Deleting movie");
        moviesService.deleteMovie(id);
    }

    @GetMapping("/searchMovieByName")
    public List<Movies> searchMovieByName(@Valid @RequestParam("title") String movieName) throws MovieNotFoundException {
        List<Movies> moviesList = moviesService.searchMovieByName(movieName);
        if(moviesList.size() == 0){
            logger.error("No movie found with that name");
            //Throwing MovieNotFoundException
            throw new MovieNotFoundException("Movie doesn't exist.");
        }

        logger.info("Returning movie");
        return moviesList;
    }
}
