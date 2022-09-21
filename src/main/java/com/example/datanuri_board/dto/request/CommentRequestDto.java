package com.example.datanuri_board.dto.request;

import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
        private Long commentId;
        private Board board;
        private User user;
        private String contents;
        private String state;

        private String creator;
        private String modifier;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public Comment toEntity() {
            Comment comments = Comment.builder()
                    .commentId(commentId)
                    .board(board)
                    .user(user)
                    .contents(contents)
                    .state(state)
                    .build();
            return comments;
        }

        @Override
        public String toString() {
                return "CommentRequestDto{" +
                        "commentId=" + commentId +
                        ", board=" + board +
                        ", user=" + user +
                        ", contents='" + contents + '\'' +
                        ", state='" + state + '\'' +
                        ", creator='" + creator + '\'' +
                        ", modifier='" + modifier + '\'' +
                        ", createdDate=" + createdDate +
                        ", modifiedDate=" + modifiedDate +
                        '}';
        }
}

