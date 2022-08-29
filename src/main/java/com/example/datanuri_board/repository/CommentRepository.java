package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserIdAndBoardId(Long userId, Long boardId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByBoardId(Long boardId);
}
