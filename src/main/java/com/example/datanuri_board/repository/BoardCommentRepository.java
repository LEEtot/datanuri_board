package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<Comment, Long> {

}
