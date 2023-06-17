package com.multiplex.ticketBooking.service;

import com.multiplex.ticketBooking.entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    Booking addBooking(Booking booking);

    Booking updateBooking(Booking booking, Long id);

    void cancelBooking(Long id);
}
