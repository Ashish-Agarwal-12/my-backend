package com.multiplex.ticketBooking.serviceImpl;


import com.multiplex.ticketBooking.entity.Slot;

import com.multiplex.ticketBooking.exception.SlotNotFoundException;
import com.multiplex.ticketBooking.repository.HallRepository;
import com.multiplex.ticketBooking.repository.MoviesRepository;
import com.multiplex.ticketBooking.repository.SlotRepository;
import com.multiplex.ticketBooking.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class slotServiceImpl implements SlotService {
    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private HallRepository hallRepository;
    @Override
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    @Override
    public Slot getSlotsById(Long id) throws SlotNotFoundException {
        Slot checkSlot =  slotRepository.findById(id).get();
        if(checkSlot == null) {
            throw new SlotNotFoundException("No such slot mapped through this ID");
        }
        return checkSlot;
    }

    @Override
    public void deleteSlot(Long id) {
        slotRepository.deleteById(id);
    }

    @Override
    public Slot updateSlots(Slot slot, Long id) {
        Slot oldSlot = slotRepository.findById(id).get();
        if(oldSlot.getCapacity() != slot.getCapacity()){
            oldSlot.setCapacity(slot.getCapacity());
        }
        if(oldSlot.getHall() != slot.getHall()){
            oldSlot.setHall(slot.getHall());
        }
        if(oldSlot.getStartTime() != slot.getStartTime()) {
            oldSlot.setStartTime(slot.getStartTime());
        }
        return slotRepository.save(oldSlot);
    }
}
