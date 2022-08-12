package com.example.datanuri_board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="board_subject_id")
    private BoardSubject boardSubject;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @NotNull
    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @Column(length = 4)
    private String state; //S001 - 활동O  S002 - block  S003 - 활동X




    @Formula("(select count(*) from recommend r where r.board_id = board_id)")
    private Long recommendCount;

    @Builder
    public Board(BoardSubject boardSubject, String title, String contents, Long view_count, LocalDateTime startDate, LocalDateTime finishDate, String state, Long recommend_count){
        this.boardSubject = boardSubject;
        this.title = title;
        this.contents = contents;
        this.viewCount = view_count;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.state = state;
        this.recommendCount = recommend_count;
    }

    public void update(BoardSubject boardSubject, String title, String contents, LocalDateTime startDate, LocalDateTime finishDate, String state, Long viewCount){
        this.boardSubject = boardSubject;
        this.title = title;
        this.contents = contents;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.state = state;
        this.viewCount = viewCount;
    }
}
