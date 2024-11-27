package com.hina.tutorial.repository;

import com.hina.tutorial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);// kiểm tra xem username đã tồn tại hay chưa
    Optional<User> findByUsername(String username); //tìm kiếm user theo username
}