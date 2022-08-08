package com.example.datanuri_board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", updatable = false)
    private Board board;

    private int comment_group;  // 댓글 그룹

    @ColumnDefault("0")
    private int comment_order;  // 댓글 내 순서

    @ColumnDefault("0")
    private int comment_layer;  // 댓글 깊이

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", updatable = false)
    private User author;
    @Column(name = "write_date")
    private LocalDateTime writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifier_id", updatable = false)
    private User modifier;

    @Column(name="modified_date")
    private LocalDateTime modifiedDate;

    @Column(length = 4)
    @Size(min = 4, max = 4)
    private String state;
    @Builder
    public Comment(String content,
                   User author,
                   LocalDateTime writeDate,
                   User modifier,
                   LocalDateTime modifiedDate,
                   String state,
                   Board board,
                   User user)
    {
        this.contents = content;
        this.author = author;
        this.writeDate = writeDate;
        this.modifier = modifier;
        this.modifiedDate = modifiedDate;
        this.state = state;
        if(this.board != null){
            board.getComments().remove(this);
        }else
            this.board = board;
        if(this.user != null){
            user.getCommentList().remove(this);
        }else
            this.user = user;
    }
}
