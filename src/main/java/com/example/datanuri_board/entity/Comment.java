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
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", updatable = false)
    private Board board;

    private int comment_group;

    private int comment_order;

    private int comment_layer;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(length = 4)
    @Size(min = 4, max = 4)
    private String state;

}
