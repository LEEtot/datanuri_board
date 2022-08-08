package com.example.datanuri_board.dto.request;

import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private Long boardSubjectId;
    private String title;
    private String contents;
    private Long view_count;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String state;

    public Board toEntity(){
        BoardSubjectRequestDto reqdto = new BoardSubjectRequestDto();
        reqdto.setId(boardSubjectId);
        BoardSubject boardSubject = reqdto.toEntity();

        return Board.builder()
                .boardSubject(boardSubject)
                .title(title)
                .contents(contents)
                .view_count(0L)
                .startDate(startDate)
                .finishDate(finishDate)
                .state(state)
                .build();
    }

}
