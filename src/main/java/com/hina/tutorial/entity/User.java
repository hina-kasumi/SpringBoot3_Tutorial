package com.hina.tutorial.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity // khai báo đây là đối tượng trong bảng
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id // khai báo field này là ID của đối tượng
    @GeneratedValue(strategy = GenerationType.UUID) // tự động tạo ID không trùng lặp cho đối tượng
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate birthDate;
}
