package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Slot;
import com.multiplex.ticketBooking.exception.SlotNotFoundException;
import com.multiplex.ticketBooking.service.SlotService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SlotController {
    public static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    @Autowired
    private SlotService slotService;

    @GetMapping("/getAllSlots")
    public List<Slot> getAllSlots() {
        logger.info("Getting all slots");
        return  slotService.getAllSlots();
    }

    @GetMapping("/getSlotById/{id}")
    public Slot getSlotsById(@PathVariable Long id) throws SlotNotFoundException {
        logger.info("Getting slot by id");
        return slotService.getSlotsById(id);
    }

    @PutMapping("/updateSlots")
    public Slot updateSlots(@Valid @RequestBody Slot slot, @PathVariable Long id) {
        logger.info("Updating slots");
        return slotService.updateSlots(slot, id);
    }

    @DeleteMapping("/deleteSlot/{id}")
    public void deleteSlot(@PathVariable Long id) {
        logger.info("Deleting slots");
        slotService.deleteSlot(id);
    }
}
