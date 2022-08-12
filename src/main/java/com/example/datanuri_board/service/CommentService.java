package com.example.datanuri_board.service;


import com.example.datanuri_board.dto.CommentDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.repository.BoardRepository;
import com.example.datanuri_board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;

    // 댓글 목록 조회
    public List<CommentDto> comments(Long boardId) {
        return commentRepository.findbyBoardId(boardId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    // 댓글 생성
    @Transactional
    public CommentDto create(Long boardId, CommentDto dto) throws IllegalAccessException {
        // 게시글 조회 및 예외 발생
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        Comment comment = Comment.creatComment(dto,board);
        Comment created = commentRepository.save(comment);

        return CommentDto.createCommentDto(created);
    }

    //댓글 수정
    @Transactional
    public CommentDto update(Long id, CommentDto dto) throws IllegalAccessException {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다!"));

        target.patch(dto);
        Comment updated = commentRepository.save(target);

        return CommentDto.createCommentDto(updated);

    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회(및 예외 발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);
    }
}
