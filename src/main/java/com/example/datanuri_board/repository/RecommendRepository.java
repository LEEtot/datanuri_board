package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {

    /**
     * 참조 변수 binding 수정
     * CommentRepository와 동일
     */

    List<Recommend> findByUserIdAndBoard_BoardId(Long userId, Long boardId);

    List<Recommend> findByUserId(Long userId);

    List<Recommend> findByBoard_BoardId(Long boardId);
}
