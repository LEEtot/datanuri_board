package com.example.datanuri_board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private String content;
    private String boardId;
    private String commentId;
}
