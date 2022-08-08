package com.example.datanuri_board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId", updatable = false)
    private User author;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifierId", updatable = false)
    private User modifier;

    @Column(name="modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @Column(length = 4)
    private String state;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments;

    public void update(String title, String comment) {
        this.title = title;
        this.comments = comments;    }


}
