package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.request.BoardSubjectRequestDto;
import com.example.datanuri_board.dto.request.CommentRequestDto;
import com.example.datanuri_board.service.BoardService;
import com.example.datanuri_board.service.BoardSubjectService;
import com.example.datanuri_board.service.CommentService;
import com.example.datanuri_board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class JspController2 {

    @Autowired
    BoardSubjectService boardSubjectService;
    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;


    @RequestMapping("/board/boardWrite")
    public ModelAndView boardsubjectList(ModelAndView mav){
        mav.setViewName("board/boardWrite");
        mav.addObject("boardSubjectBasic", boardSubjectService.findBoardSubjectByState("S001"));
        List<String> state = new ArrayList<String>();
        mav.addObject("boardSubject",boardSubjectService.listByState(state));
        return mav;
    }

    @RequestMapping("/board/boardDetail/{boardId}")
    public ModelAndView boardDetail(ModelAndView mav, @PathVariable(required = false) Long boardId){
        mav.setViewName("board/boardDetail");
        mav.addObject("boardId",boardId);
        mav.addObject("board",boardService.findBoardByBoardId(boardId));
        return mav;
    }


}

