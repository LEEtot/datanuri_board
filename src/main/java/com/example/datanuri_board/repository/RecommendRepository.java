package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    Optional<Recommend> findByBoardIdAndUserId(Long boardId, Long userId);
}
