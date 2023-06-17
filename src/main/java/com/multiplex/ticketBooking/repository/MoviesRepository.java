package com.multiplex.ticketBooking.repository;

import com.multiplex.ticketBooking.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {

    List<Movies> findByTitleContaining(String movieName);

}
