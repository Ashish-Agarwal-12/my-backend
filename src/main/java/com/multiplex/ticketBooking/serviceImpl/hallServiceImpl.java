package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.payLoads.HallPostResponse;
import com.multiplex.ticketBooking.repository.HallRepository;
import com.multiplex.ticketBooking.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class hallServiceImpl implements HallService {

    @Autowired
    private HallRepository hallRepository;
    @Override
    public HallPostResponse getAllHalls(Integer pageNumber, Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Hall> hallPage = hallRepository.findAll(p);
        List<Hall> hallList= hallPage.getContent();
        HallPostResponse hallPostResponse = new HallPostResponse();
        hallPostResponse.setContent(hallList);
        hallPostResponse.setPageNumber(hallPage.getNumber());
        hallPostResponse.setPageSize(hallPage.getSize());
        hallPostResponse.setTotalElements(hallPage.getTotalElements());
        hallPostResponse.setTotalPage(hallPage.getTotalPages());
        hallPostResponse.setLastPage(hallPage.isLast());
        return  hallPostResponse;
    }

    @Override
    public Hall getHallById(Long id) {
        return hallRepository.findById(id).get();
    }

    @Override
    public Hall updateHall(Hall hall, Long id) {
        Hall oldHall = hallRepository.findById(id).get();
        if(oldHall.getTotalCapacity() != hall.getTotalCapacity()) {
            oldHall.setTotalCapacity(hall.getTotalCapacity());
        }
        return hallRepository.save(oldHall);
    }

    @Override
    public Hall addHall(Hall hall) {
        return hallRepository.save(hall);
    }

    @Override
    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }
}
