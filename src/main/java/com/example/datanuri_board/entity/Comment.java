package com.example.datanuri_board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static javax.persistence.FetchType.LAZY;

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
    private Long commentId;  // ID

    @Setter
    @ManyToOne(optional = false)
    private Board boardId;  // 게시글(ID)
    @Setter
    @ManyToOne(optional = false)
    private User userId;  // 유저(ID)

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "commentLayer")
    private Comment parent;  // 댓글 그룹

    private String commentLayer;  //  댓글 계층

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
    @NotNull
    @Size(min = 4, max = 4)
    private String state; // 상태


    //== 부모 댓글을 삭제해도 자식 댓글은 남아있음 ==//
    @OneToMany(mappedBy = "parent")
    private List<Comment> childList = new ArrayList<>();


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
        return commentId != null && commentId == comment.commentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}
