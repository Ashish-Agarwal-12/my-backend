package com.multiplex.ticketBooking.service;

import com.multiplex.ticketBooking.entity.Movies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MoviesService {
    Movies addMovie(Movies movie);

    List<Movies> getAllMovies(Integer pageNumber, Integer pageSize);

    Movies getMovieById(Long id);

    void deleteMovie(Long id);

    Movies updateMovie(Movies movie, Long id);

    List<Movies> searchMovieByName(String movieName);
}
