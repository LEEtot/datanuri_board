package com.example.datanuri_board.repository;

import com.example.datanuri_board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
