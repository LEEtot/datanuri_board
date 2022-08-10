package com.example.datanuri_board.repository;

import com.example.datanuri_board.cache.CacheLogin;
import com.example.datanuri_board.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmailContaining(String email);
    @Cacheable(value = CacheLogin.USER, key = "#p0")
    Optional<List<User>> findByNameContaining(String name);
    List<User> findByEmailContainingOrNameContaining(String email, String name);

    Boolean existsByEmail(String email);
}
