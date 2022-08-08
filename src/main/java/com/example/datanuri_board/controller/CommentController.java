package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.CommentSaveDto;
import com.example.datanuri_board.dto.CommentUpdateDto;
import com.example.datanuri_board.exception.commentEXC.CommentException;
import com.example.datanuri_board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{boardId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void commentSave(@PathVariable("boardId") Long boardId, CommentSaveDto commentSaveDto) {
        commentService.save(boardId, commentSaveDto);
    }

    @PostMapping("/comment/{boardId}/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void reCommentSave(@PathVariable("boardId") Long boardId,
                              @PathVariable("commentId") Long commentId,
                              CommentSaveDto commentSaveDto) {
        commentService.saveReComment(boardId, commentId, commentSaveDto);
    }

    @PutMapping("/comment/{commentId}")
    public void update(@PathVariable("commentId") Long commentId,
                       CommentUpdateDto commentUpdateDto) {
        commentService.update(commentId, commentUpdateDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId) throws CommentException {
        commentService.remove(commentId);
    }
}
