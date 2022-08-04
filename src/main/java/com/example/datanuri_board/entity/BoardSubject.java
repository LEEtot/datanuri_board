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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "readAuthority")
    private Role readAuthority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writeAuthority")
    private Role writeAuthority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creatorId")
    private User creator;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modiferId")
    private User modifier;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @NotNull
    @Size(min = 4, max = 4)
    private String state;

    @Builder
    public BoardSubject(String subject, Role readAuthority, Role writeAuthority, User creator, String state){
        this.subject = subject;
        this.readAuthority = readAuthority;
        this.writeAuthority = writeAuthority;
        this.creator = creator;
        this.state = state;
    }
}
