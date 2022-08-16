package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.request.BoardRequestDto;
import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.repository.BoardRepository;
import com.example.datanuri_board.repository.BoardSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    private final BoardSubjectRepository boardSubjectRepository;

    /** 확인O
     * 게시글 생성
     */
    @Transactional
    public Long save(BoardRequestDto params){
        BoardSubject boardentity = boardSubjectRepository.findById(params.getBoardSubjectId()).orElseThrow();
        Board board = Board.builder()
                .boardSubject(boardentity)
                .title(params.getTitle())
                .contents(params.getContents())
                .view_count(0L)
                .startDate(params.getStartDate())
                .finishDate(params.getFinishDate())
                .state(params.getState())
                .build();
        Board entity = boardRepository.save(board);
        return entity.getBoardId();
    }

    /** 확인O
     * 게시글 내용수정
     */
    @Transactional
    public Long update(Long BoardId, BoardRequestDto params){
        Board entity = boardRepository.findById(BoardId).orElseThrow();
        BoardSubject boardSubject = boardSubjectRepository.findById(params.getBoardSubjectId()).orElseThrow();
        entity.update(boardSubject, params.getTitle(), params.getContents(), params.getStartDate(), params.getFinishDate(), params.getState(), params.getViewCount());
        return entity.getBoardId();
    }


    /** 조건post
     * 게시글 1개조회
     */
    public BoardResponseDto findBoardByBoardSubject_IdAndBoardIdAndState(Long BoardSubject_Id, Long Board_id){
        Board board = boardRepository.findBoardByBoardSubject_IdAndBoardIdAndState(BoardSubject_Id, Board_id, "post");
        return new BoardResponseDto(board);
    }

    /** 조건상관없이
     * 게시글 1개조회
     */
    public BoardResponseDto findBoardByBoardSubject_IdAndBoardId(Long BoardSubject_Id, Long Board_id){
        Board board = boardRepository.findBoardByBoardSubject_IdAndBoardId(BoardSubject_Id, Board_id);
        return new BoardResponseDto(board);
    }

    /** 확인O
     * 게시글 조회수 +1 수정
     */
    @Transactional
    public BoardResponseDto updateClickCount(Long BoardSubject_Id, Long Board_id){
        Board board = boardRepository.findBoardByBoardSubject_IdAndBoardIdAndState(BoardSubject_Id, Board_id, "post");
        board.update(board.getBoardSubject(), board.getTitle(), board.getContents(), board.getStartDate(), board.getFinishDate(), board.getState(), board.getViewCount()+1L );
        return new BoardResponseDto(board);
    }

    /** 조건post 확인O
     * 게시글 전체조회(최신순)
     */
    public List<BoardResponseDto> findBoardByBoardSubject_IdAndStateOrderByCreatedDateDesc(Long BoardSubject_Id , Pageable pageable){
        List<Board> list = boardRepository.findBoardByBoardSubject_IdAndStateOrderByCreatedDateDesc(BoardSubject_Id, "post", pageable);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    /** 조건post 확인O
     * 게시글 전체조회(추천순)
     */
    public List<BoardResponseDto> findRecommend(Long BoardSubject_Id, Pageable pageable){
        List<Board> list = boardRepository.findBoardByBoardSubject_IdAndStateOrderByRecommendCountDescCreatedDateDesc(BoardSubject_Id, "post", pageable);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    /** 조건post 확인O
     * 게시글 전체조회(조회순)
     */
    public List<BoardResponseDto> findBoardByBoardSubject_IdAndStateOrderByViewCountDescCreatedDateDesc(Long BoardSubject_Id, Pageable pageable){
        List<Board> list = boardRepository.findBoardByBoardSubject_IdAndStateOrderByViewCountDesc(BoardSubject_Id, "post", pageable);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    /** 조건post 확인O
     * 게시글 제목검색
     */
    public List<BoardResponseDto> findBoardByBoardSubject_IdAndStateAndTitleContainingOrderByCreatedDateDesc(Long BoardSubject_Id, String title, Pageable pageable){
        List<Board> list = boardRepository.findBoardByBoardSubject_IdAndStateAndTitleContainingOrderByCreatedDateDesc(BoardSubject_Id,"post", title, pageable);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }


    /** 조건post 확인O
     * 게시글 상위5개 뿌리기
     */
    public List<BoardResponseDto> findTop5ByBoardSubject_IdAndStateOrderByCreatedDateDesc(Long BoardSubject_Id){
        List<Board> list = boardRepository.findTop5ByBoardSubject_IdAndStateOrderByCreatedDateDesc(BoardSubject_Id, "post");
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }


    public Board getOneBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }
}
