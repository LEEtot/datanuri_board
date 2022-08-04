package com.example.datanuri_board.dto;

import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSubjectRequestDto {

    private String subject;
    private Role readAuthority;
    private Role writeAuthority;
    private User creator;
    private String state;


    public BoardSubject toEntity(){
        return BoardSubject.builder()
                .subject(subject)
                .readAuthority(readAuthority)
                .writeAuthority(writeAuthority)
                .creator(creator)
                .state(state)
                .build();
    }
}
