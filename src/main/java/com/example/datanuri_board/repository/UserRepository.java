package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmailContaining(String email);
    List<User> findByNameContaining(String name);
    List<User> findByEmailContainingOrNameContaining(String email, String name);

    Boolean existsByEmail(String email);
}
