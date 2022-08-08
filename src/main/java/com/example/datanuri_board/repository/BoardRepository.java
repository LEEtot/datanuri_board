package com.example.datanuri_board.repository;

import com.example.datanuri_board.dto.response.BoarResponseDto;
import com.example.datanuri_board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //최신순 전체조회
    List<BoarResponseDto> findAllOrderByBoard_id();

    //추천순 전체조회

    //댓글순 전체조회

    //조회순 전체조회

    //제목 검색조회
}
