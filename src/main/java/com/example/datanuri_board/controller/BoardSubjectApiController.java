package com.example.datanuri_board.controller;


import com.example.datanuri_board.dto.request.BoardSubjectRequestDto;
import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.service.BoardSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    /*@GetMapping("/list")
    public List<BoardSubjectResponseDto> findAll(){
        return boardSubjectService.findAll();
    }*/

    /**
     * 게시판 state
     */
    @GetMapping("/list/{state}")
    public List<BoardSubjectResponseDto> findAllByState(@PathVariable String state){
        return boardSubjectService.findBoardSubjectByState(state);
    }

    /**
     * 게시판 state
     */
    @GetMapping("/list/manageList")
    public Map<String,List<BoardSubjectResponseDto>> findManageList(){
        Map<String,List<BoardSubjectResponseDto>> map = new HashMap<String, List<BoardSubjectResponseDto>>();
        List<BoardSubjectResponseDto> boardSubjectResponseDtosS004 = boardSubjectService.findBoardSubjectByState("S004");
        map.put("basic", boardSubjectResponseDtosS004);

        List<String> nonbasic = new ArrayList<String>();
        nonbasic.add("S001");
        nonbasic.add("S002");
        List<BoardSubjectResponseDto> boardSubjectResponseDtosS001S002 = boardSubjectService.listByState(nonbasic);
        map.put("nonbasic", boardSubjectResponseDtosS001S002);

        return map;
    }




    /**
     * 게시판 특정 ID로 조회
     */
    @GetMapping("/select/{id}")
    public BoardSubjectResponseDto findById(@PathVariable Long id){
        return boardSubjectService.findById(id);
    }

    /**
     * 게시판 수정
     */
    @PostMapping("/update/{id}")
    public Long update(@PathVariable final Long id,@RequestBody final BoardSubjectRequestDto params){
        return boardSubjectService.update(id, params);
    }

    /**
     * 게시판 삭제 (state="S003"으로 수정)
     */
    @GetMapping("/delete/{id}")
    public Long update(@PathVariable final Long id, @RequestParam String state){
        return boardSubjectService.updateState(id, state);
    }


}
