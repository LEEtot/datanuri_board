package com.example.datanuri_board.dto;

import com.example.datanuri_board.dto.request.UserRequestDto;

public class CommentDto {
    private final static String DEFAULT_DELETE_MESSAGE = "삭제된 댓글입니다";

    private Long boardId;//댓글이 달린 POST의 ID


    private Long commentId;//해당 댓글의 ID
    private String content;//내용 (삭제되었다면 "삭제된 댓글입니다 출력")
    private boolean isRemoved;//삭제되었는지?


    private UserRequestDto AuthorDto;//댓글 작성자에 대한 정보

}

