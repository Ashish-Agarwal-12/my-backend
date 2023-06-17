package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.entity.Movies;
import com.multiplex.ticketBooking.entity.Slot;
import com.multiplex.ticketBooking.repository.HallRepository;
import com.multiplex.ticketBooking.repository.MoviesRepository;
import com.multiplex.ticketBooking.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class moviesServiceImpl implements MoviesService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public Movies addMovie(Movies movie) {

        for ( Slot slot: movie.getSlots()) {
            Hall hall = hallRepository.findById(slot.getHall().getHallId()).get();
            slot.setHall(hall);
        }
        return moviesRepository.save(movie);
    }

    @Override
    public List<Movies> getAllMovies(Integer pageNumber, Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Movies> moviesPage = moviesRepository.findAll(p);
        return moviesPage.getContent();
    }

    @Override
    public Movies getMovieById(Long id) {
        return moviesRepository.findById(id).get();
    }

    @Override
    public void deleteMovie(Long id) {
        moviesRepository.deleteById(id);
    }

    @Override
    public Movies updateMovie(Movies movie, Long id) {
        Movies oldMovie = moviesRepository.findById(id).get();
        if(oldMovie.getDescription() != movie.getDescription()){
            oldMovie.setDescription(movie.getDescription());
        }
        if(oldMovie.getDuration() != movie.getDuration()) {
            oldMovie.setDuration(movie.getDuration());
        }
        if(oldMovie.getGenre() != movie.getGenre()) {
            oldMovie.setGenre(movie.getGenre());
        }
        if(oldMovie.getTitle() != movie.getTitle()) {
            oldMovie.setTitle(movie.getTitle());
        }
        if(oldMovie.getReleaseDate() != movie.getReleaseDate()){
            oldMovie.setReleaseDate(movie.getReleaseDate());
        }
        return moviesRepository.save(oldMovie);
    }

    @Override
    public List<Movies> searchMovieByName(String movieName) {
        return moviesRepository.findByTitleContaining(movieName);
    }
}
