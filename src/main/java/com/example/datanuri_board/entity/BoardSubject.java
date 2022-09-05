package com.example.datanuri_board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "board_subject")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSubject extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String subject;

    @NotNull
    private String readAuthority; //R001 운영자  R002 관리자  R003 일반유저

    @NotNull
    private String writeAuthority; //R001  R002  R003

    @NotNull
    @Size(min = 4, max = 4)
    private String state; //S001 - 활동O  S002 - block  S003 - 활동X S004 - 메인4개.수정불가

    @Formula("(select count(*) from board b where b.board_subject_id = id and b.state ='S001')")
    private Long boardsCount;

    @Builder
    public BoardSubject(String subject, String readAuthority, String writeAuthority, String state, Long boardsCount){
        this.subject = subject;
        this.readAuthority = readAuthority;
        this.writeAuthority = writeAuthority;
        this.state = state;
        this.boardsCount = boardsCount;
    }


   public void update(String subject, String readAuthority, String writeAuthority, String state ){
        this.subject = subject;
        this.readAuthority = readAuthority;
        this.writeAuthority = writeAuthority;
        this.state = state;
    }

    @Override
    public String toString() {
        return "BoardSubject{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", readAuthority='" + readAuthority + '\'' +
                ", writeAuthority='" + writeAuthority + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
