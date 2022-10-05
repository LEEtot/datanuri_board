package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.request.BoardSubjectRequestDto;
import com.example.datanuri_board.dto.request.CommentRequestDto;
import com.example.datanuri_board.entity.User;
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
import java.util.Optional;


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


    @RequestMapping("/board/boardWrite/{boardSubjectId}")
    public ModelAndView boardsubjectList(ModelAndView mav, @PathVariable("boardSubjectId") Long boardSubjectId){
        mav.setViewName("board/boardWrite");
        mav.addObject("selectedBoardSubject",boardSubjectService.findById(boardSubjectId));
        return mav;
    }

    @RequestMapping("/board/boardDetail/{boardId}")
    public ModelAndView boardDetail(ModelAndView mav, @PathVariable(required = false) Long boardId, @PathVariable(required = false) Long userId){
        mav.setViewName("board/boardDetail");
        mav.addObject("boardId",boardId);
        mav.addObject("userId",userId);
        mav.addObject("board",boardService.findBoardByBoardId(boardId));
        mav.addObject("comment",commentService.getAllCommentsWithParam(Optional.ofNullable(userId), Optional.ofNullable(boardId)));
        mav.addObject("user",userService.findAll());
        return mav;
    }


}

