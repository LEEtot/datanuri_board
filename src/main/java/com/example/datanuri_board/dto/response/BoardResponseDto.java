package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.controller.UserController;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
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

    private String creatorName;



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
        /*this.creatorName = userService.getUserData(Long.valueOf(entity.getCreator())).getName();*/
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
