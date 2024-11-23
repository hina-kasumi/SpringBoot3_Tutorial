package com.hina.tutorial.controller;

import com.hina.tutorial.dto.request.UserCreationRequest;
import com.hina.tutorial.dto.request.UserUpdateRequest;
import com.hina.tutorial.entity.User;
import com.hina.tutorial.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createRequest(request);
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId){
        return userService.updateUser(request, userId);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.DeleteUser(userId);
        return "User deleted";
    }
}
