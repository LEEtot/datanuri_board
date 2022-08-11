package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.CommentDto;
import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.service.BoardService;
import com.example.datanuri_board.service.BoardSubjectService;
import com.example.datanuri_board.service.CommentService;
import com.example.datanuri_board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final BoardService boardService;
    private final BoardSubjectService boardSubjectService;

    @PostMapping("/create")
    public String createComment(CommentDto commentDto, HttpSession httpSession) {

        String loginId = String.valueOf(Long.parseLong(httpSession.getId()));
        User author = userService.findById(Long.valueOf(loginId));

        BoardSubjectResponseDto board = boardSubjectService.findById(Long.valueOf(commentDto.getBoardId()));
        commentService.createComment(commentDto,board,author);

        long boardId=  Long.parseLong(commentDto.getBoardId());

        return "redirect:/post/read?post="+boardId;
    }

    @PostMapping("/edit")
    public String editComment(@RequestParam String comment, @RequestParam String content) {

        long commentId = Long.parseLong(comment);
        commentService.editComment(commentId, content);

        long boardId = commentService.findById(commentId).getBoard().getBoardId();
        return "redirect:/board/read?board="+boardId;
    }

    @GetMapping("/delete")
    public String deleteComment(@RequestParam String comment,
                                HttpSession httpSession) {

        long commentId = Long.parseLong(comment);
        Comment commentToDelete = commentService.findById(commentId);


        long boardId = commentToDelete.getBoard().getBoardId();
        commentService.deleteComment(commentId);
        return "redirect:/board/read?board="+boardId;
    }
}
