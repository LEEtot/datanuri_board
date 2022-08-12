package com.example.datanuri_board.dto;

import com.example.datanuri_board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private Long boardId;
    private String author;
    private String content;
    private String creator;
    private LocalDateTime createdDate;
    private String modifier;
    private LocalDateTime modifiedDate;
    private String state;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getBoard().getBoardId(),
                comment.getAuthor(),
                comment.getContent(),
                comment.getCreator(),
                comment.getCreatedDate(),
                comment.getModifier(),
                comment.getModifiedDate(),
                comment.getState()
        );
    }
}
