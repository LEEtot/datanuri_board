package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 참조 에러
     * User 엔티티의 id 변수 명: id
     * Board 엔티티 id 변수 명: boardId
     * 저도 정확하게는 모르지만 id일 경우 기본적으로 지원하는 기능이 있지만
     * 다른 변수명을 id로 사용하는 경우 아래처럼 명시해줘야하는것 같습니다.
     * {참조 class 이름}_{참조 변수 이름}
     *
     * 수정 내용
     * findByUserIdAndBoardId ->
     * findByUserIdAndBoard_BoardId
     *
     * findByBoardId ->
     * findByBoard_BoardId
     */
    List<Comment> findByUserIdAndBoard_BoardId(Long userId, Long boardId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByBoard_BoardId(Long boardId);
}
