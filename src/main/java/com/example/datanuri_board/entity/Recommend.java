package com.example.datanuri_board.entity;

import lombok.Setter;

import javax.persistence.*;

@Entity
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long recommendId;
    @Setter
    @ManyToOne(optional = false)
    private Board boardId;  // 게시글(ID)
    @Setter
    @ManyToOne(optional = false)
    private User userId;  // 유저(ID)
}
