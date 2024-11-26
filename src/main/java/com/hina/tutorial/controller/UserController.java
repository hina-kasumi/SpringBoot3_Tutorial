package com.hina.tutorial.controller;

import com.hina.tutorial.dto.request.ApiResponse;
import com.hina.tutorial.dto.request.UserCreationRequest;
import com.hina.tutorial.dto.request.UserUpdateRequest;
import com.hina.tutorial.dto.response.UserResponse;
import com.hina.tutorial.entity.User;
import com.hina.tutorial.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        // @RequestBody để chuyển dữ liệu json sang đối tượng java
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createRequest(request));
        return apiResponse;
    }

    @GetMapping
    List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId){
        //sử dụng @PathVariable để lấy Path ID được truyền từ đường dẫn
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId){
        // @RequestBody để chuyển dữ liệu json sang đối tượng java
        //sử dụng @PathVariable để lấy Path ID được truyền từ đường dẫn
        return userService.updateUser(request, userId);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        //sử dụng @PathVariable để lấy Path ID được truyền từ đường dẫn
        userService.DeleteUser(userId);
        return "User deleted";
    }
}
