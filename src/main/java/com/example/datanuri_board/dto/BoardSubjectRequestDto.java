package com.example.datanuri_board.dto;

import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSubjectRequestDto {

    private Long id;
    private String subject;
    private String readAuthority;
    private String writeAuthority;
    private Long creator;
    private String state;
    private User modifier;
    private LocalDateTime modifiedDate;


    public BoardSubject toEntity(){
        return BoardSubject.builder()
                .subject(subject)
                .readAuthority(Role.builder().id(readAuthority).build())
                .writeAuthority(Role.builder().id(writeAuthority).build())
                .creator(User.builder().id(creator).build())
                .state(state)
                .build();
    }


}
