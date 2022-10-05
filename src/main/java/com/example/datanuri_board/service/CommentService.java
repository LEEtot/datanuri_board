package com.example.datanuri_board.service;


import com.example.datanuri_board.dto.request.BoardRequestDto;
import com.example.datanuri_board.dto.request.CommentRequestDto;
import com.example.datanuri_board.dto.response.CommentResponseDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.repository.BoardRepository;
import com.example.datanuri_board.repository.CommentRepository;
import com.example.datanuri_board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentService {

    /**
     * 생성자는 주로 롬복(rombok) 어노테이션을 활용합니다.
     * <p>
     * 주로 사용하는 생성자 어노테이션은
     *
     * @RequiredArgsConstructor
     * @NoArgsConstructor
     * @AllArgsConstructor 이렇게 3가지이고 인터넷에 간단하게 검색하시면 잘 나와있습니다.
     * <p>
     * 기존 함수명을 활용한 생성자도 동작하지만 특별한 상황이 아니면 의존성을 주입할때는 controller에 명시한 주입 방법을 사용합니다.
     * <p>
     * 수정 내용
     * contructor 삭제
     * @RequiredArgsConstructor + final 명시
     * <p>
     * repository 함수 수정 -> service 내 함수명 수정
     */

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    private final UserService userService;
    private final BoardService boardService;

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> boardId) {
        if (userId.isPresent() && boardId.isPresent()) {
            return commentRepository.findByUserIdAndBoard_BoardId(userId.get(), boardId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (boardId.isPresent()) {
            return commentRepository.findByBoard_BoardId(boardId.get());
        } else {
            return commentRepository.findAll();
        }
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Transactional
    public Long createOneComment(CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(commentRequestDto.getUserId()).orElse(null);
        Optional<Board> board = boardRepository.findById(commentRequestDto.getBoardId());
        Comment comment = Comment.builder()
                .board(board.get())
                .user(user)
                .author(user.getName())
                .contents(commentRequestDto.getContents())
                .state(commentRequestDto.getState())
                .build();
        Comment Entity = commentRepository.save(comment);

        return Entity.getCommentId();
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
