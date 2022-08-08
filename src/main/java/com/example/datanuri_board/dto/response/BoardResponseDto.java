package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.BoardSubject;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long board_id;
    private BoardSubject boardSubject;
    private String title;
    private String contents;
    private Long view_count;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String state;


    public BoardResponseDto(Long board_id, BoardSubject boardSubject, String title, String contents, Long view_count, LocalDateTime startDate, LocalDateTime finishDate, String state) {
        this.board_id = board_id;
        this.boardSubject = boardSubject;
        this.title = title;
        this.contents = contents;
        this.view_count = view_count;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.state = state;
    }


    @Override
    public String toString() {
        return "BoarResponseDto{" +
                "board_id=" + board_id +
                ", boardSubject=" + boardSubject +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", view_count=" + view_count +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", state='" + state + '\'' +
                '}';
    }
}
