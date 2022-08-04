package com.example.datanuri_board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    private Long board_id;

    @ManyToOne
    @JoinColumn(name="board_subject_id")
    private BoardSubject boardSubject;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @NotNull
    private Long view_count;

    @NotNull
    private String author;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    private String modifier;

    @Column(name="modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @Column(length = 4)
    private String state;

}
