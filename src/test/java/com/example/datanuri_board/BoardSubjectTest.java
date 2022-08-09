package com.example.datanuri_board;

import com.example.datanuri_board.dto.request.BoardRequestDto;
import com.example.datanuri_board.dto.request.BoardSubjectRequestDto;
import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.repository.BoardSubjectRepository;
import com.example.datanuri_board.service.BoardService;
import com.example.datanuri_board.service.BoardSubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BoardSubjectTest {

    @Autowired
    BoardSubjectService boardSubjectService;

    @Autowired
    BoardService boardService;

    @Test
    public void find(){
        BoardSubjectRequestDto requestDto = new BoardSubjectRequestDto();
        requestDto.setSubject("testSubject");
        requestDto.setCreator("main");
        requestDto.setState("stat");
        requestDto.setWriteAuthority("main");
        requestDto.setReadAuthority("main");

        boardSubjectService.save(requestDto);

        List<BoardSubjectResponseDto> list = boardSubjectService.findAll();

        for(BoardSubjectResponseDto res : list){
            System.out.println(res.toString());
        }

        BoardSubjectRequestDto modiDto = new BoardSubjectRequestDto();
        modiDto.setSubject("수정된주제");
        modiDto.setState("modi");
        boardSubjectService.update(1L,requestDto);

        System.out.print("수정된=================");

        BoardSubjectResponseDto responseDto = boardSubjectService.findById(1L);
        System.out.println("ResponseDto========="+responseDto.toString());

       BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setBoardSubjectId(responseDto.getId());
        boardRequestDto.setTitle("새로만든 게시글타이틀1");
        boardRequestDto.setContents("새로만든 내용1");
        boardRequestDto.setState("tttt");
        boardRequestDto.setStartDate(LocalDateTime.now());
        boardRequestDto.setFinishDate(LocalDateTime.now());
        System.out.println(boardRequestDto.toString());

        boardService.save(boardRequestDto);
        System.out.println("보드1 저장");

        List<BoardResponseDto> boards = boardService.findBoardByBoardSubjectOrderByCreatedDate(1L);
        System.out.println("boards 뿌리기");
        for (BoardResponseDto board : boards){
            System.out.println(board.toString());
        }
    }
}
