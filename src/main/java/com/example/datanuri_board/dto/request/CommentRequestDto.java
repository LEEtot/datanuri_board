package com.example.datanuri_board.dto.request;

import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentRequestDto {
    private Long commentId;
    private Board board;
    private String contents;
    private User user;
    private String state;
    private String creator;
    private String modifier;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
