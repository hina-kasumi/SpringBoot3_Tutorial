package com.hina.tutorial.service;

import com.hina.tutorial.dto.request.UserCreationRequest;
import com.hina.tutorial.dto.request.UserUpdateRequest;
import com.hina.tutorial.entity.User;
import com.hina.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createRequest(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByUsername((request.getUsername())))
            throw new RuntimeException("Username already exists");

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());

        return userRepository.save(user);
    }

    public List<User> getUsers (){
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(UserUpdateRequest request, String userId) {
        User user = getUser(userId);
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());

        return userRepository.save(user);
    }

    public void DeleteUser(String id) {
        userRepository.deleteById(id);
    }
}
