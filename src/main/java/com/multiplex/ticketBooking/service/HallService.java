package com.multiplex.ticketBooking.service;

import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.payLoads.HallPostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HallService {
    HallPostResponse getAllHalls(Integer pageNumber, Integer pageSize);

    Hall getHallById(Long id);

    Hall updateHall(Hall hall, Long id);

    Hall addHall(Hall hall);

    void deleteHall(Long id);
}
