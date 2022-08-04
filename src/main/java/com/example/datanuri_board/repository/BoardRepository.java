package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
