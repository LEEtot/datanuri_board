package com.example.datanuri_board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recommend")
public class Recommend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long recommend_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private User user;  // 유저(ID)

    @ManyToOne(optional = false)
    @JoinColumn(name = "boardId")
    private Board board;  // 게시글(ID)
}
