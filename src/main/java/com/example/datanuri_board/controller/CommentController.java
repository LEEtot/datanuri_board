package com.example.datanuri_board.controller;


import com.example.datanuri_board.dto.CommentDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class CommentController {
    @PostMapping("/board/{boardId}/detail")
    public String createComment(@PathVariable Long boardId,
                                @ModelAttribute("form") CommentDto commentDto) {

        Board findBoard = boardService.findOne(boardId);

        CommentService.save(findBoard, commentDto.getContent());
        return "redirect:/board/" + boardId + "/detail";
    }


}
