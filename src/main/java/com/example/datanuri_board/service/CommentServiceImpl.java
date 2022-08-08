package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.CommentSaveDto;
import com.example.datanuri_board.dto.CommentUpdateDto;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.exception.commentEXC.CommentException;
import com.example.datanuri_board.exception.commentEXC.CommentExceptionType;
import com.example.datanuri_board.repository.BoardRepository;
import com.example.datanuri_board.repository.CommentRepository;
import com.example.datanuri_board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository memberRepository;
    private final BoardRepository postRepository;

    @Override
    public void save(Long postId, CommentSaveDto commentSaveDto) {

    }

    @Override
    public void saveReComment(Long postId, Long parentId, CommentSaveDto commentSaveDto) {

    }

    @Override
    public void update(Long id, CommentUpdateDto commentUpdateDto) {

    }

    @Override
    public void remove(Long id) throws CommentException {

    }
}