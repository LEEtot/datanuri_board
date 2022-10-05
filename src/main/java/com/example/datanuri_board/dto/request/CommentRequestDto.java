package com.example.datanuri_board.dto.request;


import lombok.NoArgsConstructor;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private Long commentId;
    private Long boardId;
    private String contents;
    private Long userId;
    private String state;

    private String author;
    private String creator;
    private String modifier;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentRequestDto(Long commentId, Long boardId, String author, String contents, Long userId, String state) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.contents = contents;
        this.author = author;
        this.userId = userId;
        this.state = state;
    }

    @Override
    public String toString() {
        return "CommentRequestDto{" +
                "commentId=" + commentId +
                ", boardId=" + boardId +
                ", contents='" + contents + '\'' +
                ", userId=" + userId +
                ", state='" + state + '\'' +
                ", author='" + author + '\'' +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
