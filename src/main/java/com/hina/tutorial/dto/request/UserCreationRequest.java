package com.hina.tutorial.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class UserCreationRequest {
    @Size(min = 3, message = "Username must be at least 3 character")
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
