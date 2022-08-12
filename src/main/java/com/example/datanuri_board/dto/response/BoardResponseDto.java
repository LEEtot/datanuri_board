package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long boardId;
    private BoardSubject boardSubject;
    private String title;
    private String contents;
    private Long viewCount;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String state;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String creator;
    private String modifier;

    //추천수 값
    private Long recommendCount;

    public BoardResponseDto(Board entity) {
        this.boardId = entity.getBoardId();
        this.boardSubject = entity.getBoardSubject();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.viewCount = entity.getViewCount();
        this.startDate = entity.getStartDate();
        this.finishDate = entity.getFinishDate();
        this.state = entity.getState();
        this.recommendCount = entity.getRecommendCount();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        this.creator = entity.getCreator();
        this.modifier = entity.getModifier();

    }

    @Override
    public String toString() {
        return "BoardResponseDto{" +
                "boardId=" + boardId +
                ", boardSubject=" + boardSubject +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", viewCount=" + viewCount +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", state='" + state + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", recommendCount=" + recommendCount +
                '}';
    }
}
