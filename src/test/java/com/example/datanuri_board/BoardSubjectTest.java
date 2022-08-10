package com.example.datanuri_board;

import com.example.datanuri_board.dto.request.BoardRequestDto;
import com.example.datanuri_board.dto.request.BoardSubjectRequestDto;
import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.repository.BoardSubjectRepository;
import com.example.datanuri_board.service.BoardService;
import com.example.datanuri_board.service.BoardSubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebAppConfiguration
@SpringBootTest
public class BoardSubjectTest {

    @Autowired
    BoardSubjectService boardSubjectService;

    @Autowired
    BoardService boardService;

    @Test
    public void find(){
        


        //게시판 생성
        BoardSubjectRequestDto requestDto = new BoardSubjectRequestDto();
        requestDto.setSubject("testSubject");
        requestDto.setCreator("main");
        requestDto.setState("post");
        requestDto.setWriteAuthority("main");
        requestDto.setReadAuthority("main");

        boardSubjectService.save(requestDto);

        List<BoardSubjectResponseDto> list = boardSubjectService.findAll();

        for(BoardSubjectResponseDto res : list){
            System.out.println(res.toString());
        }

        BoardSubjectRequestDto modiDto = new BoardSubjectRequestDto();
        modiDto.setSubject("수정수정");
        modiDto.setCreator("main");
        modiDto.setState("post");
        modiDto.setWriteAuthority("main");
        modiDto.setReadAuthority("main");
        boardSubjectService.update(1L,modiDto);

        System.out.print("수정된 게시판=================");

        BoardSubjectResponseDto responseDto = boardSubjectService.findById(1L);
        System.out.println("ResponseDto========="+responseDto.toString());




        //게시글 생성
       BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setBoardSubjectId(responseDto.getId());
        boardRequestDto.setTitle("새로만든 게시글타이틀1");
        boardRequestDto.setContents("새로만든 내용1");
        boardRequestDto.setState("post");
        boardRequestDto.setStartDate(LocalDateTime.now());
        boardRequestDto.setFinishDate(LocalDateTime.now());
        System.out.println(boardRequestDto.toString());

        boardService.save(boardRequestDto);
        System.out.println("보드1 저장");

        boardRequestDto.setTitle("새로만든 게시글 타이틀222222222");
        boardService.save(boardRequestDto);
        System.out.println("보드2 저장");

        Pageable pageable = PageRequest.of(0, 5);

        //게시글 최신순으로 전체조회
        List<BoardResponseDto> boardLast = boardService.findBoardByBoardSubject_IdAndStateOrderByCreatedDateDesc(1L, pageable);
        for(BoardResponseDto board : boardLast){

            System.out.println("board최신순" + board.toString());
        }


        boardRequestDto.setTitle("수정한 게시글타이틀");
        boardRequestDto.setContents("수정한게시");
        boardRequestDto.setViewCount(0L);
        boardService.update(1L, boardRequestDto);

        //게시글 전체조회(추천순)
        List<BoardResponseDto> boards2 = boardService.findRecommend(1L, pageable);
        System.out.println("boards 뿌리기");
        for (BoardResponseDto board : boards2){
            System.out.println(board.toString());
        }

        //게시글 조회수 +1
        BoardResponseDto boardupdateDto = boardService.updateClickCount(1L, 1L);
        System.out.println("게시글 조회수 +1"+boardupdateDto.toString());

        //게시글 조회순 전체조회
        List<BoardResponseDto> boardCount = boardService.findBoardByBoardSubject_IdAndStateOrderByViewCountDescCreatedDateDesc(1L, pageable);
        for(BoardResponseDto board : boardCount){

            System.out.println("board조회순" + board.toString());
        }

        //게시글 제목검색 state=post
        List<BoardResponseDto> responseList = boardService.findBoardByBoardSubject_IdAndStateAndTitleContainingOrderByCreatedDateDesc(1L, "22222", pageable);
        for(BoardResponseDto board : responseList){

            System.out.println("제목검색====" + board.toString());
        }

        //게시글 상위5개 state=post
        List<BoardResponseDto> responseList2 = boardService.findTop5ByBoardSubject_IdAndStateOrderByCreatedDateDesc(1L);
        for(BoardResponseDto board : responseList2){

            System.out.println("상위5개====" + board.toString());
        }
    }
}
