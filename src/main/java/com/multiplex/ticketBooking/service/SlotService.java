package com.multiplex.ticketBooking.service;

import com.multiplex.ticketBooking.entity.Slot;
import com.multiplex.ticketBooking.exception.SlotNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SlotService {
    List<Slot> getAllSlots();

    Slot getSlotsById(Long id) throws SlotNotFoundException;

    void deleteSlot(Long id);

    Slot updateSlots(Slot slot, Long id);
}
