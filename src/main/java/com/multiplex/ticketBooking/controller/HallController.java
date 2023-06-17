package com.multiplex.ticketBooking.controller;


import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.payLoads.HallPostResponse;
import com.multiplex.ticketBooking.service.HallService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HallController {
    @Autowired
    private HallService hallService;

    public static final Logger logger = LoggerFactory.getLogger(HallController.class);

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.POST)
    @PostMapping("/addHall")
    public Hall addHall(@Valid @RequestBody Hall hall){
        logger.info("Creating a new Hall");
        return hallService.addHall(hall);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.GET)
    @GetMapping("/getAllHalls")
    public ResponseEntity<HallPostResponse> getAllHalls(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        logger.info("Retrieving all Halls");
        HallPostResponse hallPostResponse = hallService.getAllHalls(pageNumber, pageSize);
        return new ResponseEntity<HallPostResponse>(hallPostResponse, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.GET)
    @GetMapping("/getHallById/{id}")
    public Hall getHallById(@PathVariable Long id) {
        logger.info("Retrieving a particular Hall By id");
        return hallService.getHallById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.PUT)
    @PutMapping("/updateHall/{id}")
    public Hall updateHall(@RequestBody Hall hall, @PathVariable Long id) {
        logger.info("Updating the Hall By id");
        return hallService.updateHall(hall, id);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.DELETE)
    @DeleteMapping("/deleteHall/{id}")
    public String deleteHall(@PathVariable Long id) {
        logger.info("Deleting the hall");
        hallService.deleteHall(id);
        return "Deleted Successfully";
    }
}
