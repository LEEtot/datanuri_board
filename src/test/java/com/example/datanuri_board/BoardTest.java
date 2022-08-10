package com.example.datanuri_board;

import com.example.datanuri_board.dto.request.BoardRequestDto;
import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardTest {

    @Autowired
    BoardService boardService;

    @Test
    public void boardTest(){
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setBoardSubjectId(1L);
        boardRequestDto.setTitle("새로만든 게시글타이틀1");
        boardRequestDto.setContents("새로만든 내용1");
        boardRequestDto.setState("tttt");
        //boardRequestDto.set

        boardService.save(boardRequestDto);
        System.out.println("보드1 저장");

        //List<BoardResponseDto> boards = boardService.findBoardByBoardSubject_IdOrderByCreatedDate(1L);


    }
}
