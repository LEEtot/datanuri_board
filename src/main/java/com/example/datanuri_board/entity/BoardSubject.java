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
public class BoardSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String subject;

    @NotNull
    @Column(name = "read_authority")
    private Long readAuthority;

    @NotNull
    @Column(name = "write_authority")
    private Long writeAuthority;

    @NotNull
    private String creator;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    private String modifier;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @NotNull
    @Column(length = 4)
    private String state;

    @Builder
    public BoardSubject(String subject, Long readAuthority, Long writeAuthority, String creator, String state){
        this.subject = subject;
        this.readAuthority = readAuthority;
        this.writeAuthority = writeAuthority;
        this.creator = creator;
        this.state = state;
    }
}
