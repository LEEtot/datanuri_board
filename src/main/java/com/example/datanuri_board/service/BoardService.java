package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.request.BoardRequestDto;
import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.repository.BoardRepository;
import com.example.datanuri_board.repository.BoardSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardSubjectRepository boardSubjectRepository;

    /**
     * 게시글 생성
     */
    public Long save(BoardRequestDto params){
        Board entity = boardRepository.save(params.toEntity());
        return entity.getBoard_id();
    }

    /**
     * 게시글 내용수정
     */
    public Long update(final Long id, final BoardRequestDto params){
        Board entity = boardRepository.findById(id).orElseThrow();
        Long Id = params.getBoardSubjectId();
        BoardSubject boardSubject = boardSubjectRepository.findById(Id).orElseThrow();
        entity.update(boardSubject, params.getTitle(), params.getContents(), params.getStartDate(), params.getFinishDate(), params.getState());
        return entity.getBoard_id();
    }

    /**
     * 게시글 전체조회(최신순)
     */
    /*public List<BoardResponseDto> findAllById(){

    }*/

}
