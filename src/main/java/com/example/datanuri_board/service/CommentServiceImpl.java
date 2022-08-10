package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.CommentSaveDto;
import com.example.datanuri_board.dto.CommentUpdateDto;
import com.example.datanuri_board.dto.security.SecurityUtil;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.exception.BoardEXC.BoardException;
import com.example.datanuri_board.exception.BoardEXC.BoardExceptionType;
import com.example.datanuri_board.exception.UserEXC.UserException;
import com.example.datanuri_board.exception.UserEXC.UserExceptionType;
import com.example.datanuri_board.exception.commentEXC.CommentException;
import com.example.datanuri_board.exception.commentEXC.CommentExceptionType;
import com.example.datanuri_board.repository.BoardRepository;
import com.example.datanuri_board.repository.CommentRepository;
import com.example.datanuri_board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Override
    public void save(Long boardId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmAuthor((User) userRepository.findByNameContaining(SecurityUtil.getLoginUsername()).orElseThrow(()
                -> new UserException(UserExceptionType.NOT_FOUND_USER)));

        comment.confirmBoard(boardRepository.findById(boardId).orElseThrow(()
                -> new BoardException(BoardExceptionType.BOARD_NOT_FOUND)));

        commentRepository.save(comment);
    }

    @Override
    public void saveReComment(Long postId, Long parentId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmAuthor((User) userRepository.findByNameContaining(SecurityUtil.getLoginUsername()).orElseThrow(()
                -> new UserException(UserExceptionType.NOT_FOUND_USER)));

        comment.confirmBoard(boardRepository.findById(postId).orElseThrow(()
                -> new BoardException(BoardExceptionType.BOARD_NOT_FOUND)));

        comment.confirmParent(commentRepository.findById(parentId).orElseThrow(()
                -> new CommentException(CommentExceptionType.NOT_POUND_COMMENT)));

        commentRepository.save(comment);
    }

    @Override
    public void update(Long id, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(()
                -> new CommentException(CommentExceptionType.NOT_POUND_COMMENT));

        if(!comment.getAuthor().getName().equals(SecurityUtil.getLoginUsername())){
            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_UPDATE_COMMENT);
        }

        commentUpdateDto.content().ifPresent(comment::updateContent);

    }

    @Override
    public void remove(Long id) throws CommentException {
        Comment comment = commentRepository.findById(id).orElseThrow(()
                -> new CommentException(CommentExceptionType.NOT_POUND_COMMENT));

        if(!comment.getAuthor().getName().equals(SecurityUtil.getLoginUsername())){
            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_DELETE_COMMENT);
        }

        comment.remove();
        List<Comment> removableCommentList = comment.findRemovableList();
        commentRepository.deleteAll(removableCommentList);
    }
}