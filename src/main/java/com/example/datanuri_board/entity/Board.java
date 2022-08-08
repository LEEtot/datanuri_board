package com.example.datanuri_board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_subject_id")
    private BoardSubject boardSubject;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @NotNull
    private Long view_count;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @Column(length = 4)
    private String state;

    @Builder
    public Board(BoardSubject boardSubject, String title, String contents, Long view_count, LocalDateTime startDate, LocalDateTime finishDate, String state){
        this.boardSubject = boardSubject;
        this.title = title;
        this.contents = contents;
        this.view_count = view_count;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.state = state;
    }

    public void update(BoardSubject boardSubject, String title, String contents, LocalDateTime startDate, LocalDateTime finishDate, String state){
        this.boardSubject = boardSubject;
        this.title = title;
        this.contents = contents;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.state = state;
    }
}
