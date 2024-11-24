package com.hina.tutorial.mapper;

import com.hina.tutorial.dto.request.UserCreationRequest;
import com.hina.tutorial.dto.request.UserUpdateRequest;
import com.hina.tutorial.dto.response.UserResponse;
import com.hina.tutorial.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
