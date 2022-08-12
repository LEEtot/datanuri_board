package com.example.datanuri_board.entity;


import com.example.datanuri_board.dto.CommentDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;  // comment id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;  // board id

    @Column
    private String author;  // author

    @Column
    private String content;  // content

    @Column(length = 4) //Post, Block, Delete
    private String state;  //   state

    public static Comment creatComment(CommentDto dto, Board board) throws IllegalAccessException {
        // 예외 발생
        if(dto.getId() != null)
            throw new IllegalAccessException("댓글 생성 실패!");
        if(dto.getBoardId() != board.getBoardId())
            throw  new IllegalAccessException("댓글 생성 실패! 게시글의 ID가 잘못되었습니다.");

        return new Comment(
                dto.getId(),
                board,
                dto.getAuthor(),
                dto.getContent(),
                dto.getState()
        );
    }

    public void patch(CommentDto dto) throws IllegalAccessException {
        // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalAccessException("댓글 수정 실패! 잘못된 ID 값입니다.");

        if (dto.getAuthor() != null)
            this.author = dto.getAuthor();
        if (dto.getContent() != null)
            this.content = dto.getContent();
    }
}
