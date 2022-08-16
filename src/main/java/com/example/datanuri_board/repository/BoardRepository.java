package com.example.datanuri_board.repository;


import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //게시글 한개조회(조건상태)
    Board findBoardByBoardIdAndState(Long Board_id, String state);

    //게시글 한개조회(state-상관없이)
    Board findBoardByBoardId(Long Board_id);

    //최신순 전체조회(조건상태)
    Page<Board> findBoardByBoardSubject_IdAndStateOrderByCreatedDateDesc(Long BoardSubject_Id, String state, Pageable pageable);

    //추천순 전체조회(조건상태)
    Page<Board> findBoardByBoardSubject_IdAndStateAndTitleContainingIgnoreCaseOrderByRecommendCountDescCreatedDateDesc(Long BoardSubject_Id, String state, String title, Pageable pageable);

    //조회순 전체조회(조건상태)
    Page<Board> findBoardByBoardSubject_IdAndStateAndTitleContainingIgnoreCaseOrderByViewCountDesc(Long BoardSubject_Id, String state, String title, Pageable pageable);

    //제목 검색조회(조건상태)
    Page<Board> findBoardByBoardSubject_IdAndStateAndTitleContainingIgnoreCaseOrderByCreatedDateDesc(Long BoardSubject_Id,String state, String title, Pageable pageable);

    //상위4개 게시판+게시글5개 뿌리기
    List<Board> findTop5ByBoardSubject_IdAndStateOrderByCreatedDateDesc(Long BoardSubject_Id, String state);


//    작성자 본인 게시글리스트 조회
    Page<Board> findBoardByCreatorAndStateAndBoardSubject_IdInOrderByCreatedDate(String creator, String state, List<Long> BoardSubject_Ids, Pageable pageable);


}
