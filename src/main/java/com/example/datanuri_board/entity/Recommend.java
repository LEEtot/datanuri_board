package com.example.datanuri_board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@Table(name="p_like")
public class Recommend {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    //hibernate mapingleri
    @ManyToOne(fetch = FetchType.LAZY)//user nesnesi hemen gelmesin.
    @JoinColumn(name = "board_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)//user silinirse onun tüm postları da silinecektir.
    @JsonIgnore
    Board borad;

    @ManyToOne(fetch = FetchType.LAZY)//user nesnesi hemen gelmesin.
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)//user silinirse onun tüm postları da silinecektir.
    @JsonIgnore
    User user;

}

