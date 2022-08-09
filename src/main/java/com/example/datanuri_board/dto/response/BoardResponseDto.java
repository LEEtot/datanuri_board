package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Board;
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


    public BoardResponseDto(Board entity) {
        this.board_id = entity.getBoard_id();
        this.boardSubject = entity.getBoardSubject();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.view_count = entity.getView_count();
        this.startDate = entity.getStartDate();
        this.finishDate = entity.getFinishDate();
        this.state = entity.getState();
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
