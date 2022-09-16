package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.request.BoardRequestDto;
import com.example.datanuri_board.dto.response.BoardResponseDto;
import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.dto.response.UserResponseDto;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.BoardSubject;
import com.example.datanuri_board.repository.BoardRepository;
import com.example.datanuri_board.repository.BoardSubjectRepository;
import com.example.datanuri_board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    private final BoardSubjectRepository boardSubjectRepository;

    private final BoardSubjectService boardSubjectService;

    private final UserRepository userService;

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

    /** 확인O
     * 게시글 State 수정 (삭제)
     */
    @Transactional
    public Long updateState(Long BoardId, String state){
        Board entity = boardRepository.findById(BoardId).orElseThrow();
        BoardSubject boardSubject = boardSubjectRepository.findById(entity.getBoardSubject().getId()).orElseThrow();
        entity.update(boardSubject, entity.getTitle(), entity.getContents(), entity.getStartDate(), entity.getFinishDate(), state, entity.getViewCount());
        return entity.getBoardId();
    }


    /** 조건post
     * 게시글 1개조회
     */
    public BoardResponseDto findBoardByBoardIdAndState(Long Board_id, String state){
        Board board = boardRepository.findBoardByBoardIdAndState(Board_id, state);
        return new BoardResponseDto(board);
    }

    /** 조건상관없이
     * 게시글 1개조회
     */
    public BoardResponseDto findBoardByBoardId(Long Board_id){
        Board board = boardRepository.findBoardByBoardId(Board_id);
        return new BoardResponseDto(board);
    }

    /** 확인O
     * 게시글 조회수 +1 수정
     */
    @Transactional
    public BoardResponseDto updateClickCount(Long Board_id){
        Board board = boardRepository.findBoardByBoardId(Board_id);
        board.update(board.getBoardSubject(), board.getTitle(), board.getContents(), board.getStartDate(), board.getFinishDate(), board.getState(), board.getViewCount()+1L );
        return new BoardResponseDto(board);
    }

    /** 조건post 확인O
     * 게시글 전체조회(최신순)
     */
    public Page<BoardResponseDto> findBoardByBoardSubject_IdAndStateOrderByCreatedDateDesc(Long BoardSubject_Id , Pageable pageable){
        Page<Board> boardPage = boardRepository.findBoardByBoardSubject_IdAndStateOrderByCreatedDateDesc(BoardSubject_Id, "S001", pageable);
        Page<BoardResponseDto> boardResponseDtos = boardPage.map(board -> new BoardResponseDto(board));
        return boardResponseDtos;
    }

    /** 조건post 확인O
     * 게시글 전체조회(추천순)
     */
    public Page<BoardResponseDto> findRecommend(Long BoardSubject_Id,  String title, Pageable pageable){
        Page<Board> list = boardRepository.findBoardByBoardSubject_IdAndStateAndTitleContainingIgnoreCaseOrderByRecommendCountDescCreatedDateDesc(BoardSubject_Id, "S001", title, pageable);
        return list.map(board -> new BoardResponseDto(board));
    }

    /** 조건post 확인O
     * 게시글 전체조회(조회순)
     */
    public Page<BoardResponseDto> findBoardByBoardSubject_IdAndStateOrderByViewCountDescCreatedDateDesc(Long BoardSubject_Id, String title, Pageable pageable){
        Page<Board> list = boardRepository.findBoardByBoardSubject_IdAndStateAndTitleContainingIgnoreCaseOrderByViewCountDesc(BoardSubject_Id, "S001", title, pageable);
        return list.map(board -> new BoardResponseDto(board));
    }

    /** 조건post 확인O
     * 게시글 제목검색
     */
    public Page<BoardResponseDto> findBoardByBoardSubject_IdAndStateAndTitleContainingOrderByCreatedDateDesc(Long BoardSubject_Id, String title, Pageable pageable){
        Page<Board> list = boardRepository.findBoardByBoardSubject_IdAndStateAndTitleContainingIgnoreCaseOrderByCreatedDateDesc(BoardSubject_Id,"S001", title, pageable);
        return list.map(board -> new BoardResponseDto(board));
    }


    /** 조건post 확인O
     * 게시글 상위5개 뿌리기
     */
    public List<BoardResponseDto> findTop5ByBoardSubject_IdAndStateOrderByCreatedDateDesc(Long BoardSubject_Id){
        List<Board> list = boardRepository.findTop5ByBoardSubject_IdAndStateOrderByCreatedDateDesc(BoardSubject_Id, "S001");
        List<BoardResponseDto> boards = list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        /*for(BoardResponseDto board : boards){
            System.out.println(board.getCreator());
            Long boardId = Long.parseLong(board.getCreator());
            UserResponseDto userDto = UserResponseDto.of(userService
                    .findById(boardId)
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다")));
            board.setCreatorName(userDto.getName());
            //board.setCreatorName().getName());
        }*/
        return boards;
    }

    /**
     * 게시판 리스트 - state = "S001" "S004"
     * 게시글리스트 - 작성자
     */
    public Page<BoardResponseDto> findBoardByCreator(String creator, Pageable pageable){
        List<String> state = new ArrayList<>();
        state.add("S001");
        state.add("S004");
        List<BoardSubjectResponseDto> boardSubjects = boardSubjectService.listByState(state);


        List<Long> boardSubject_Ids = new ArrayList<>();
        for(BoardSubjectResponseDto boardSubject : boardSubjects){
            System.out.println("boardId=================="+boardSubject.getId());
            boardSubject_Ids.add(boardSubject.getId());
        }

        Page<Board> boards = boardRepository.findBoardByCreatorAndStateAndBoardSubject_IdInOrderByCreatedDate(creator, "S001", boardSubject_Ids, pageable);
        return boards.map(board -> new BoardResponseDto(board));
    }

    public Board getOneBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }
}
