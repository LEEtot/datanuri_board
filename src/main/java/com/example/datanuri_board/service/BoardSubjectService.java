package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.BoardSubjectRequestDto;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.repository.BoardSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor /** final로 선언된 모든 멤버에 대해 생성자를 자동생성 */
public class BoardSubjectService {

    private final BoardSubjectRepository boardSubjectRepository;

    /**
     * 게시판 생성
     */
    @Transactional
    public Long save(final BoardSubjectRequestDto params){
        BoardSubject entity = boardSubjectRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 게시판 전체조회
     */

}
