package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.exception.UserNotCreatedException;
import com.multiplex.ticketBooking.exception.UserNotFoundException;
import com.multiplex.ticketBooking.repository.UserRepository;
import com.multiplex.ticketBooking.service.UserService;
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
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.POST)
    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody User user) throws UserNotCreatedException {
        logger.info("Creating user");
        User checkUser =  userService.createUser(user);
        if(checkUser.getUserId() <= 0){
            logger.error("User not created");
            throw new UserNotCreatedException("Entered User Couldn't be Created");
        }
        return checkUser;
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.GET)
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUser(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        logger.info("Getting all users");
        List<User> userList =  userService.getAllUsers(pageNumber, pageSize);
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.GET)
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        logger.info("Getting user by id");
        User checkUser = userService.getUserById(id);
        if(checkUser == null){
            logger.error("User cannot be found");
            throw new UserNotFoundException("Entered User doesn't exist");
        }
        logger.info("Getting user by id");
        return checkUser;
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.PUT)
    @PutMapping("/updateUserById/{id}")
    public User updateUserById(@Valid @RequestBody User user, @PathVariable Long id) {
        logger.info("Updating user");
        return userService.updateUserById(user, id);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.DELETE)
    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable Long id) {
        logger.info("Deleting user");
        userService.deleteUserById(id);
    }
}
