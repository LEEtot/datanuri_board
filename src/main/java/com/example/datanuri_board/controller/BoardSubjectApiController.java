package com.example.datanuri_board.controller;


import com.example.datanuri_board.dto.BoardSubjectRequestDto;
import com.example.datanuri_board.dto.BoardSubjectResponseDto;
import com.example.datanuri_board.service.BoardSubjectService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boardSubject")
@RequiredArgsConstructor
public class BoardSubjectApiController {

    private final BoardSubjectService boardSubjectService;




    /**
     * 게시판 생성
     */
    @PostMapping("/save")
    public Long save(@RequestBody final BoardSubjectRequestDto params){
        return boardSubjectService.save(params);
    }

    /**
     * 게시판 전체조회
     */
    @GetMapping("/list")
    public List<BoardSubjectResponseDto> findAll(){
        return boardSubjectService.findAll();
    }


    /**
     * 게시판 수정
     */
    @PostMapping("/update/{id}")
    public Long update(@PathVariable final Long id,@RequestBody final BoardSubjectRequestDto params){
        return boardSubjectService.update(id, params);
    }
}
