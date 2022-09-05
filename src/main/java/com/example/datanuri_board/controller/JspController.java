package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.TokenDto;
import com.example.datanuri_board.dto.request.BoardSubjectRequestDto;
import com.example.datanuri_board.dto.request.UserRequestDto;
import com.example.datanuri_board.service.AuthService;
import com.example.datanuri_board.service.BoardSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JspController {

    @Autowired
    BoardSubjectService boardSubjectService;

    @Autowired
    AuthService authService;

    @RequestMapping("/")
    public String main(){
        return "main";
    }

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "/login";
    }

    @RequestMapping("/signupPage")
    public String signupPage(){
        return "/signupPage";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "/noAuth";
    }


    @RequestMapping("/board/boardList/{boardSubjectId}")
    public ModelAndView boardList(ModelAndView mav,@PathVariable(required = false) Long boardSubjectId){
        mav.setViewName("board/boardList");
        mav.addObject("boardSubjectId",boardSubjectId);
        mav.addObject("boardSubject",boardSubjectService.findById(boardSubjectId));
        return mav;
    }

    @RequestMapping(value = "/boardsubject/boardsubjectList")
    public ModelAndView boardsubjectList(ModelAndView mav){
        mav.setViewName("boardsubject/boardsubjectList");
        mav.addObject("boardSubjectBasic", boardSubjectService.findBoardSubjectByState("S004"));
        List<String> state = new ArrayList<String>();
        state.add("S001");
        state.add("S002");
        mav.addObject("boardSubjectModi",boardSubjectService.listByState(state));
        return mav;
    }

    @RequestMapping(value="/boardsubject/save", method = RequestMethod.POST)
    public String boardsubjectSave(BoardSubjectRequestDto requestDto){
        boardSubjectService.save(requestDto);

        return "redirect:boardsubjectList";
    }

    @RequestMapping(value="/boardsubject/update", method = RequestMethod.POST)
    public String boardsubjectUpdate(BoardSubjectRequestDto requestDto){
        boardSubjectService.update(requestDto.getId(), requestDto);

        return "redirect:boardsubjectList";
    }

    @RequestMapping(value="/boardsubject/delete")
    public String boardsubjectDelete(@RequestParam Long boardsubjectId){
        boardSubjectService.updateState(boardsubjectId, "S003");

        return "redirect:boardsubjectList";
    }

    @RequestMapping(value = "/user/userList")
    public ModelAndView userList(ModelAndView mav){
        mav.setViewName("user/userList");
        /*mav.addObject("boardSubjectBasic", boardSubjectService.findBoardSubjectByState("S004"));
        List<String> state = new ArrayList<String>();
        state.add("S001");
        state.add("S002");
        mav.addObject("boardSubjectModi",boardSubjectService.listByState(state));*/
        return mav;
    }
}
