package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentResponseDto {
    private Long commentId;
    private Long board;
    private String contents;
    private String author;
    private String state;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentResponseDto(Comment entity) {
        this.commentId = entity.getCommentId();
        this.board = entity.getBoard().getBoardId();
        this.contents = entity.getContents();
        this.author = entity.getAuthor();
        this.state = entity.getState();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
