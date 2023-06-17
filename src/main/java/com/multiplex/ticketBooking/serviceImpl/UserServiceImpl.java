package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.repository.UserRepository;
import com.multiplex.ticketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers(Integer pageNumber, Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepository.findAll(p);
        return userPage.getContent();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User createUser(User user) {
        return  userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(User user, Long id) {
        User oldUser = userRepository.findById(id).get();
        if(oldUser.getUserType() != user.getUserType()) {
            oldUser.setUserType(user.getUserType());
        }
        if(oldUser.getUserName() != user.getUserName()) {
            oldUser.setUserName(user.getUserName());
        }
        if(oldUser.getPassword() != user.getPassword()) {
            oldUser.setPassword(user.getPassword());
        }
        if(oldUser.getEmailId() != user.getEmailId()) {
            oldUser.setEmailId(user.getEmailId());
        }
        if(oldUser.getMobileNumber() != user.getMobileNumber()){
            oldUser.setMobileNumber((user.getMobileNumber()));
        }
        return userRepository.save(oldUser);
    }
}
