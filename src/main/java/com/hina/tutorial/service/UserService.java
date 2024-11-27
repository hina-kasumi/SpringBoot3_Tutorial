package com.hina.tutorial.service;

import com.hina.tutorial.dto.request.UserCreationRequest;
import com.hina.tutorial.dto.request.UserUpdateRequest;
import com.hina.tutorial.dto.response.UserResponse;
import com.hina.tutorial.entity.User;
import com.hina.tutorial.exception.AppException;
import com.hina.tutorial.exception.ErrorCode;
import com.hina.tutorial.mapper.UserMapper;
import com.hina.tutorial.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createRequest(UserCreationRequest request) {
        // kiểm tra xem username đã tồn tại hay chưa
        if (userRepository.existsByUsername((request.getUsername())))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    // trả về danh sách user
    public List<UserResponse> getUsers (){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    // trả về danh sách user theo ID
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"))); // nếu không tìm thấy thì throw exception
    }

    public UserResponse updateUser(UserUpdateRequest request, String userId) {
        // kiểm tra nếu username không tồn tại
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));// nếu không tìm thấy thì throw exception
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void DeleteUser(String id) {
        userRepository.deleteById(id); // xóa dữ liệu user theo ID
    }
}
