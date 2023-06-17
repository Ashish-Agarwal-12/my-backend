package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Ticket;
import com.multiplex.ticketBooking.service.TicketService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    public static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    @Autowired
    private TicketService ticketService;

    @GetMapping("/getAllTicketsByUserName/{userName}")
    public List<Ticket> getAllTicketsByUserName(@Valid @RequestParam("userName") String userName){
        logger.info("Getting all tickets");
        List<Ticket> tickets = ticketService.getAllTicketsByUserName(userName);
        if (tickets.isEmpty()){
            logger.error("No such ticket found");
            return null;
        }
        logger.info("Getting all tickets");
        return tickets;
    }

    @GetMapping("/getTicketById/{ticketId}")
    public Ticket getAllTicketsById(@PathVariable Long ticketId){
        logger.info("Getting all tickets");
        Ticket ticket = ticketService.getAllTicketsById(ticketId);
        if (ticket == null){
            logger.info("No tickets found");
            return null;
        }
        logger.info("Getting all tickets");
        return ticket;
    }

}
