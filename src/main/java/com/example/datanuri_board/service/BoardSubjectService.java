package com.example.datanuri_board.service;

import com.example.datanuri_board.config.exception.CustomException;
import com.example.datanuri_board.config.exception.ErrorCode;
import com.example.datanuri_board.dto.BoardSubjectRequestDto;
import com.example.datanuri_board.dto.BoardSubjectResponseDto;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.repository.BoardSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<BoardSubjectResponseDto> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createDate");
        List<BoardSubject> list = boardSubjectRepository.findAll(sort);
        return list.stream().map(BoardSubjectResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 게시판 수정
     */
    /*@Transactional
    public Long update(final Long id, final BoardSubjectRequestDto params){
        BoardSubject entity = boardSubjectRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BoardSubject_NOT_FOUND));
        entity.update(params.getSubject(),params.getReadAuthority(),params.getWriteAuthority(),params.getModifier(), params.getState());
        return params.getId();
    }*/

}
