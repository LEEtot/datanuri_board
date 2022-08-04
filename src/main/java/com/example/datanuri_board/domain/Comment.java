package com.example.datanuri_board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "contents"),
        @Index(columnList = "write_data"),
        @Index(columnList = "author")
})

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long comment_id;  // ID

    @Setter
    @ManyToOne(optional = false)
    private Board board_id;  // 게시글(ID)
    @Setter
    @ManyToOne(optional = false)
    private User user_id;  // 유저(ID)

    private int comment_group; // 댓글 그룹
    private int comment_order; // 그룹내 순서
    private int comment_layer; // 댓글 계층

    @Setter
    @Column(nullable = false, length = 2000)
    private String contents; // 내용
    @CreatedBy
    @Column(nullable = false, length = 50)
    private String author; // 작성자
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime write_date; // 작성 일자
    @LastModifiedBy
    @Column(nullable = false, length = 50)
    private String modifier; // 수정자
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifier_date;  // 수정 일자

    @Setter
    @Column(length = 4)
    private String state; // 상태

    protected Comment() {
    }

    private Comment(String contents) {
        this.contents = contents;
    }

    public static Comment of(String contents) {
        return new Comment(contents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return comment_id != null && comment_id == comment.comment_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment_id);
    }
}
