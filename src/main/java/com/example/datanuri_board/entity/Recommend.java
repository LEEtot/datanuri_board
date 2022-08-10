package com.example.datanuri_board.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "recommend")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Recommend implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_id", nullable = false)
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "board_id", nullable = false)
    private long boardId;
}
