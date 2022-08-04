package com.example.datanuri_board;

import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.entity.BoardSubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BoardSubjectTest {

    @Autowired
    BoardSubjectRepository boardSubjectRepository;

    @Test
    void save(){
        BoardSubject params = BoardSubject.builder()
                .subject("기본게시판2")
                .readAuthority(2L)
                .writeAuthority(2L)
                .creator("운영자")
                .state("Y")
                .build();

        boardSubjectRepository.save(params);

        BoardSubject entity = boardSubjectRepository.findById((long) 1).get();



    }
}
