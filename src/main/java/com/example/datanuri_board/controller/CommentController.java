package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.CommentDto;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //  댓글 목록 조회
    @GetMapping("/{boardId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long boardId) {

        List<CommentDto> getCommentData = commentService.comments(boardId);

        return ResponseEntity.status(HttpStatus.OK).body(getCommentData);
    }
    //  댓글 생성
    @PostMapping("/{boardId}/create")
    public ResponseEntity<CommentDto> create(@PathVariable Long boardId,
                                             @RequestBody CommentDto dto) throws IllegalAccessException {
        CommentDto createDto = commentService.create(boardId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    //  댓글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto) throws IllegalAccessException {
        // 서비스에게 위임
        CommentDto updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    //  댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        // 서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
