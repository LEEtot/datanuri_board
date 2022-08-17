package com.example.datanuri_board.service;


import com.example.datanuri_board.dto.request.CommentRequestDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.repository.CommentRepository;
import com.example.datanuri_board.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentService {
    private CommentRepository commentRepository;

    private UserRepository userRepository;

    private UserService userService;
    private BoardService boardService;

    public CommentService(CommentRepository commentRepository, UserService userService, BoardService boardService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.boardService = boardService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndBoardId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }


    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

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
