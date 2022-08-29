package com.example.datanuri_board.dto.request;

import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
public class BoardSubjectRequestDto {

    private Long id;
    private String subject;
    private String readAuthority;
    private String writeAuthority;
    private String creator;
    private String state;
    private User modifier;
    private Long boardsCount;


    public BoardSubject toEntity(){
        return BoardSubject.builder()
                .subject(subject)
                .readAuthority(readAuthority)
                .writeAuthority(writeAuthority)
                .state(state)
                .build();
    }


}
