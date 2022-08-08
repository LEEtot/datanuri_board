package com.example.datanuri_board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "board_subject")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSubject extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String subject;

    @NotNull
    private String readAuthority;

    @NotNull
    private String writeAuthority;

    @NotNull
    @Size(min = 4, max = 4)
    private String state;

    @Builder
    public BoardSubject(String subject, String readAuthority, String writeAuthority, String state){
        this.subject = subject;
        this.readAuthority = readAuthority;
        this.writeAuthority = writeAuthority;
        this.state = state;
    }
}
