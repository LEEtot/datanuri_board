package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class CommentResponseDto {
    private Long commentId;
    private Long boardId;
    private Long userId;
    private String contents;
    private String state;
    private String creator;
    private String modifier;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.contents = comment.getContents();
        this.state = comment.getState();
        this.boardId = comment.getBoard().getBoardId();
        this.userId = comment.getUser().getId();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.creator = comment.getCreator();
        this.modifier = comment.getModifier();
    }
}
