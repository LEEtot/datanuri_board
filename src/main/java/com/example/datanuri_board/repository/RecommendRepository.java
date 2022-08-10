package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Recommend;
import com.example.datanuri_board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {

    Optional<Recommend> findByUserAndBoradId(User user, String boardId);
}
