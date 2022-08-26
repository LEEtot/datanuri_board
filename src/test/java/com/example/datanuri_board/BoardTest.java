package com.example.datanuri_board;

import com.example.datanuri_board.dto.request.BoardRequestDto;
import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.service.BoardService;
import com.example.datanuri_board.service.BoardSubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
public class BoardTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardSubjectService boardSubjectService;

    @Test
    public void boardTest(){
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<BoardResponseDto> boardRequestDtos = boardService.findBoardByCreator("main", pageRequest);
        for(BoardResponseDto boards : boardRequestDtos){
            System.out.println("페이지처리되어 보드리스폰스 : "+boards);
        }


    }
}
