package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.request.RecommendRequest;
import com.example.datanuri_board.dto.response.RecommendResponse;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.Recommend;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.repository.BoardRepository;
import com.example.datanuri_board.repository.RecommendRepository;
import com.example.datanuri_board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecommendService {

    /**
     * Repository 함수명 수정 -> service 내 함수명 수정
     *
     */


    @Autowired
    private RecommendRepository recommendRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    public List<RecommendResponse> getAllRecommendWithParam(Optional<Long> userId, Optional<Long> boardId) {
        List<Recommend> list;
        if(userId.isPresent() && boardId.isPresent()){
            list = recommendRepository.findByUserIdAndBoard_BoardId(userId.get(),boardId.get());
        } else if (userId.isPresent()) {
            list = recommendRepository.findByUserId(userId.get());
        } else if (boardId.isPresent()) {
            list = recommendRepository.findByBoard_BoardId(boardId.get());
        }else
            list = recommendRepository.findAll();
        return list.stream().map(RecommendResponse::new).collect(Collectors.toList());
    }



    public Recommend createOneRecommend(RecommendRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        Board board = boardRepository.findBoardByBoardId(request.getBoardId());

        if (user != null && board != null) {
            Recommend recommendToSave = new Recommend();
            recommendToSave.setId(request.getId());
            recommendToSave.setBoard(board);
            recommendToSave.setUser(user.get());

            return recommendRepository.save(recommendToSave);
        } else return null;
    }

    public Recommend getOneRecommendById(Long recommendId) {
        return recommendRepository.findById(recommendId).orElse(null);
    }

    public void deleteOneRecommendById(Long recommendId) {
        recommendRepository.deleteById(recommendId);
    }
}
