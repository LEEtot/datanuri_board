package com.example.datanuri_board.service;


import com.example.datanuri_board.dto.request.CommentRequestDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.repository.CommentRepository;
import com.example.datanuri_board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    public CommentService(CommentRepository commentRepository, UserService userService, BoardService boardService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.boardService = boardService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> boardId) {
        if (userId.isPresent() && boardId.isPresent()) {
            return commentRepository.findByUserIdAndBoardId(userId.get(), boardId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (boardId.isPresent()) {
            return commentRepository.findByBoardId(boardId.get());
        } else {
            return commentRepository.findAll();
        }


    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Transactional
    public Comment createOneComment(CommentRequestDto commentRequestDto) {
        User user = userService.findById(Long.valueOf(commentRequestDto.getAuthor()));
        Board board = boardService.getOneBoardById(commentRequestDto.getBoard().getBoardId());
        if (user != null && board != null) {
            Comment commentToSave = new Comment();
            commentToSave.setCommentId(commentRequestDto.getCommentId());
            commentToSave.setBoard(board);
            commentToSave.setAuthor(user.getName());
            commentToSave.setContents(commentToSave.getContents());
            return commentRepository.save(commentToSave);
        } else
            return null;
    }

    @Transactional
    public Comment updateOneCommentById(Long commentId, CommentRequestDto commentRequestDto) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setContents(commentRequestDto.getContents());
            return commentRepository.save(commentToUpdate);

        } else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
