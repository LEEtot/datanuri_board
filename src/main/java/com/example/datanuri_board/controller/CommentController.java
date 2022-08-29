package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.request.CommentRequestDto;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    /**
     * 에러 1. 의존성 주입 시
     * @Autowired 어노테이션
     * or
     * @RequiredArgsConstructor + 변수에 final 표기
     */
    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> boardId){
        return commentService.getAllCommentsWithParam(userId,boardId);
    }

    @GetMapping("")
    public Comment getOneComment(@PathVariable Long commentId){
        return  commentService.getOneCommentById(commentId);
    }


    @PostMapping("")
    public Comment createOneComment(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.createOneComment(commentRequestDto);
    }

    @PutMapping("")
    public Comment updateOneComment(@PathVariable Long commentId,@RequestBody CommentRequestDto commentRequestDto){
        return  commentService.updateOneCommentById(commentId,commentRequestDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }

}
