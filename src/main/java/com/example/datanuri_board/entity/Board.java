package com.example.datanuri_board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

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

    @OneToMany(mappedBy = "board")
    private List<Comment> comments;

    //== 게시글을 삭제하면 달려있는 댓글 모두 삭제 ==//
    @OneToMany(mappedBy = "board", cascade = ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    //== 연관관계 편의 메서드 ==//
    public void addComment(Comment comment){
        //comment의 Post 설정은 comment에서 함
        commentList.add(comment);
    }
}
