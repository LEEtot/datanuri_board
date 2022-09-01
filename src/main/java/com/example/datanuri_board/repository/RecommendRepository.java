package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {

    List<Recommend> findByUserIdAndBoardId(Long userId, Long boardId);

    List<Recommend> findByUserId(Long userId);

    List<Recommend> findByBoardId(Long boardId);
}
