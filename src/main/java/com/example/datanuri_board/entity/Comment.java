package com.example.datanuri_board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", updatable = false)
    private Board board;

    private int comment_group;

    private int comment_order;

    private int comment_layer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId", updatable = false)
    private User author;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;


    @Column(name = "write_date")
    private LocalDateTime writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifierId", updatable = false)
    private User modifier;

    @Column(name="modified_date")
    private LocalDateTime modifiedDate;


    @Column(length = 4)
    @Size(min = 4, max = 4)
    private String state;

}
