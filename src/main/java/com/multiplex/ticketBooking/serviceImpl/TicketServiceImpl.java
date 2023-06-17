package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Ticket;
import com.multiplex.ticketBooking.repository.TicketRepository;
import com.multiplex.ticketBooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTicketsByUserName(String userName) {
        return ticketRepository.findAllByUserName(userName);
    }

    @Override
    public Ticket getAllTicketsById(Long ticketId) {
        return ticketRepository.findById(ticketId).get();
    }
}
