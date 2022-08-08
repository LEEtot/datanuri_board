package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardSubjectResponseDto {

    private Long id;
    private String subject;
    private String readAuthority;
    private String writeAuthority;
    private String creator;
    private LocalDateTime createdDate;
    private String modifier;
    private LocalDateTime modifiedDate;
    private String state;

    public BoardSubjectResponseDto(BoardSubject entity){
        this.id = entity.getId();
        this.subject = entity.getSubject();
        this.readAuthority = entity.getReadAuthority();
        this.writeAuthority = entity.getWriteAuthority();
        this.creator = entity.getCreator();
        this.createdDate = entity.getCreatedDate();
        this.modifier = entity.getModifier();
        this.modifiedDate = entity.getModifiedDate();
        this.state = entity.getState();
    }

    @Override
    public String toString() {
        return "BoardSubjectResponseDto{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", readAuthority='" + readAuthority + '\'' +
                ", writeAuthority='" + writeAuthority + '\'' +
                ", creator='" + creator + '\'' +
                ", createdDate=" + createdDate +
                ", modifier='" + modifier + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", state='" + state + '\'' +
                '}';
    }
}
