package com.example.datanuri_board.dto;

import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardSubjectResponseDto {

    private Long id;
    private String subject;
    private Role readAuthority;
    private Role writeAuthority;
    private User creator;
    private LocalDateTime createDate;
    private User modifier;
    private LocalDateTime modifiedDate;
    private String state;

    public BoardSubjectResponseDto(BoardSubject entity){
        this.id = entity.getId();
        this.subject = entity.getSubject();
        this.readAuthority = entity.getReadAuthority();
        this.writeAuthority = entity.getWriteAuthority();
        this.creator = entity.getCreator();
        this.createDate = entity.getCreateDate();
        this.modifier = entity.getModifier();
        this.modifiedDate = entity.getModifiedDate();
        this.state = entity.getState();
    }


}
