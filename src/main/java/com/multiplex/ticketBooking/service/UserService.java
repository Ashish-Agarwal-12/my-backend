package com.multiplex.ticketBooking.service;

import com.multiplex.ticketBooking.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers(Integer pageNumber, Integer pageSize);

    User getUserById(Long id);

    User createUser(User user);

    void deleteUserById(Long id);

    User updateUserById(User user, Long id);
}
