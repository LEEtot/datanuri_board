package com.example.datanuri_board.dto.request;

import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.repository.BoardSubjectRepository;
import com.example.datanuri_board.service.BoardSubjectService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {

    private Long boardSubjectId;
    private String title;
    private String contents;
    private Long viewCount;
    private Long recommend_count;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String creator;
    private String state;
    private String modifier;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    /*@Autowired
    BoardSubjectRepository boardSubjectRepository;

    public Board toEntity(){
        BoardSubject entity = boardSubjectRepository.findById(boardSubjectId).orElseThrow();

        return Board.builder()
                .boardSubject(entity)
                .title(title)
                .contents(contents)
                .view_count(0L)
                .startDate(startDate)
                .finishDate(finishDate)
                .state(state)
                .build();
    }*/

    @Override
    public String toString() {
        return "BoardRequestDto{" +
                "boardSubjectId=" + boardSubjectId +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", view_count=" + viewCount +
                ", recommend_count=" + recommend_count +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", creator='" + creator + '\'' +
                ", state='" + state + '\'' +
                ", modifier=" + modifier +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
