package com.example.datanuri_board.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;  // comment id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String contents;  // content

    @Column
    private String author;  // author

    @Column(length = 4) //Post, Block, Delete
    private String state;  //   state

    @Builder
    public Comment(Board board, User user, String contents, String author, String state) {
        this.board = board;
        this.user = user;
        this.contents = contents;
        this.author = author;
        this.state = state;
    }

    public void update(String contents) {
        this.contents = contents;
    }

}


