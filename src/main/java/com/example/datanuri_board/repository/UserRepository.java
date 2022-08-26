package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmailContaining(String email);

    List<User> findByNameContaining(String name);
    List<User> findByEmailContainingOrNameContaining(String email, String name);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
