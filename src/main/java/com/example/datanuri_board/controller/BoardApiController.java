package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.request.BoardRequestDto;
import com.example.datanuri_board.dto.request.BoardSubjectRequestDto;
import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.service.BoardService;
import com.example.datanuri_board.service.BoardSubjectService;
import com.example.datanuri_board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardSubjectService boardSubjectService;

    @Autowired
    UserService userService;

    /**
     *  main페이지에 4개 게시판 *5개 게시글 리스트
     */
    @GetMapping("/mainList")
    public Map<Long,List<BoardResponseDto>> mainList() {

        Map<Long,List<BoardResponseDto>> map = new HashMap<Long, List<BoardResponseDto>>();
        List<BoardSubjectResponseDto> boardSubjectList = boardSubjectService.findBoardSubjectByStateAndReadAuthority("S004","R003");
        for (BoardSubjectResponseDto boardSubject : boardSubjectList){

            Long boardSubjectId = boardSubject.getId();
            List<BoardResponseDto> boardList = boardService.findTop5ByBoardSubject_IdAndStateOrderByCreatedDateDesc(boardSubjectId);
            /*for(BoardResponseDto boards : boardList){
                //boards.setCreatorName(userService.getUserData(Long.valueOf(boards.getCreator())).getName());
                System.out.println((boards.getCreator()));
            }*/
            map.put(boardSubjectId, boardList);

        }

        return map;
    }

    /**
     *  해당 게시판ID를 통해 게시글LIST 조회 (생성일Desc, title, state ="S001")
     */
    @GetMapping("/listLatest/{boardSubjectId}")
    public Page<BoardResponseDto> listByBoardSubject_id(@PathVariable Long boardSubjectId, @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam(value = "title", required = false)String title){
        PageRequest pageRequest = PageRequest.of(page, size);
        return boardService.findBoardByBoardSubject_IdAndStateAndTitleContainingOrderByCreatedDateDesc(boardSubjectId, title, pageRequest);

    }

    /**
     *  해당 게시판ID를 통해 게시글LIST 조회 (추천수Desc, title, state ="S001")
     */
    @GetMapping("/listRecommend/{boardSubjectId}")
    public Page<BoardResponseDto> listByBoardSubject_idOrderByRecommendCount(@PathVariable Long boardSubjectId, @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam(value = "title", required = false)String title){
        PageRequest pageRequest = PageRequest.of(page, size);
        return boardService.findRecommend(boardSubjectId, title, pageRequest);

    }

    /**
     *  해당 게시판ID를 통해 게시글LIST 조회 (조회수Desc, title, state ="S001")
     */
    @GetMapping("/listView/{boardSubjectId}")
    public Page<BoardResponseDto> listByBoardSubject_idOrderByViewCount(@PathVariable Long boardSubjectId, @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam(value = "title", required = false)String title){
        PageRequest pageRequest = PageRequest.of(page, size);
        return boardService.findBoardByBoardSubject_IdAndStateOrderByViewCountDescCreatedDateDesc(boardSubjectId, title, pageRequest);

    }

    /**
     *  자신이 작성한 게시글List 조회. 게시판의 state = "S001","S004"
     */
    @GetMapping("/listByCreator")
    public Page<BoardResponseDto> findBoardByCreator(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam String creator){
        PageRequest pageRequest = PageRequest.of(page, size);
        return boardService.findBoardByCreator(creator, pageRequest);

    }

    /**
     *  게시글List에서 1개의 게시글 select + 조회수 1씩 증가
     */
    @GetMapping("/get/{boardId}")
    public BoardResponseDto findBoardByBoardId(@PathVariable Long boardId){
        boardService.updateClickCount(boardId);
        return boardService.findBoardByBoardId(boardId);

    }


    /**
     *  게시글 작성
     */
    @PostMapping("/save")
    public Long saveBoard(@RequestBody BoardRequestDto boardRequestDto){
        boardRequestDto.setViewCount(0L);
        boardRequestDto.setRecommend_count(0L);
        return boardService.save(boardRequestDto);

    }

    /**
     *  게시글 update
     */
    @PostMapping("/update/{boardId}")
    public Long updateBoardByBoardId(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto){
        return boardService.update(boardId, boardRequestDto);

    }

    /**
     *  게시글 S003(delete)
     */
    @GetMapping("/delete/{boardId}")
    public Long deleteBoardByBoardId(@PathVariable Long boardId, @RequestParam String state){
        return boardService.updateState(boardId, "S003");
    }

    /**
     *  게시글 S002(block)
     */
    @GetMapping("/block/{boardId}")
    public Long blockBoardByBoardId(@PathVariable Long boardId){
        return boardService.updateState(boardId, "S002");
    }

    /**
     *  게시글 S001(active)
     */
    @GetMapping("/active/{boardId}")
    public Long activeBoardByBoardId(@PathVariable Long boardId){
        return boardService.updateState(boardId, "S001");
    }
}
