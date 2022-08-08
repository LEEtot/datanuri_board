package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select max(c.comment_group) from Comment c")
    Optional<Integer> findCommentGroup();


}
