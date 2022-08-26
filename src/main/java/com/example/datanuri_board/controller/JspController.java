package com.example.datanuri_board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JspController {

    @RequestMapping("/")
    public String main(){
        return "main";
    }


    @RequestMapping("/board/boardList/{boardSubjectId}")
    public ModelAndView boardList(ModelAndView mav,@PathVariable(required = false) Long boardSubjectId){
        mav.setViewName("board/boardList");
        mav.addObject("boardSubjectId",boardSubjectId);
        return mav;
    }
}
