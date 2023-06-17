package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Booking;
import com.multiplex.ticketBooking.entity.Slot;
import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.repository.BookingRepository;
import com.multiplex.ticketBooking.repository.SlotRepository;
import com.multiplex.ticketBooking.repository.UserRepository;
import com.multiplex.ticketBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class bookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public Booking addBooking(Booking booking) {
        Optional<Slot> slot = slotRepository.findById(booking.getSlot().getSlotId());
        if (slot.isPresent()){
            booking.setSlot(slot.get());
        }
        Optional<User> user = userRepository.findById(booking.getUser().getUserId());
        if(user.isPresent()) {
            booking.setUser(user.get());
        }
        booking.setStatus("Confirmed");
        Booking savedBooking = bookingRepository.save(booking);
        if (savedBooking == null){
            return null;//throw new Exception
        }
        //updating the number of seats left for a slot after booking
        savedBooking.getSlot().setCapacity(savedBooking.getSlot().getCapacity() - savedBooking.getNoOfSeats());

        slotRepository.save(savedBooking.getSlot());
        return savedBooking;
    }

    @Override
    public Booking updateBooking(Booking booking, Long id) {
        Booking oldBooking = bookingRepository.findById(id).get();

        if(oldBooking.getStatus() != booking.getStatus()) {
            oldBooking.setStatus(booking.getStatus());
        }
        if(oldBooking.getBookingDate() != booking.getBookingDate()) {
            oldBooking.setBookingDate(booking.getBookingDate());
        }
        booking.setBookingDate(LocalDate.now());
        return bookingRepository.save(oldBooking);
    }

    @Override
    public void cancelBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (!booking.isPresent()){
            return;
        }
        booking.get().setStatus("Cancelled");
        bookingRepository.save(booking.get());
        //update back the number of seats in slot
        Slot slot = slotRepository.findById(booking.get().getSlot().getSlotId()).get();
        slot.setCapacity(slot.getCapacity() + booking.get().getNoOfSeats());
        slotRepository.save(slot);
    }

}
