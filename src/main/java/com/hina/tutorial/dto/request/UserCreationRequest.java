package com.hina.tutorial.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE) // gán giá trị các field trong class là private
public class UserCreationRequest {
    @Size(min = 3, message = "USER_INVALID") //username phải ít nhất 3 kí tự,message phải truyền vào là một ký tự
    String username;

    @Size(min = 8, message = "INVALID_PASSWORD") //password ít nhất phải 8 kí tự, message phải truyền vào là một ký tự
    String password;
    String firstName;
    String lastName;
    LocalDate birthDate;
}
