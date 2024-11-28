package com.hina.tutorial.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)// gán giá trị các field trong class là private
public class UserResponse {
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate birthDate;
}
