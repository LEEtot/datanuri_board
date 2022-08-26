package com.example.datanuri_board.repository;

import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.entity.BoardSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardSubjectRepository extends JpaRepository<BoardSubject, Long> {

    //게시판 state - S001만조회 (일반회원), block만조회 (운영자,관리자)
     List<BoardSubject> findBoardSubjectByStateOrderByIdAsc(String state);

    List<BoardSubject> findTop4ByStateAndReadAuthority(String state, String readAuthority);

    
    //게시판 리스트 - state에따라
    List<BoardSubject> findBoardSubjectByStateInOrderByCreatedDate(List<String> state);
}
