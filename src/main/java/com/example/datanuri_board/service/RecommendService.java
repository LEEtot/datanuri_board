package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.request.RecommendRequest;
import com.example.datanuri_board.dto.response.RecommendResponse;
import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.Recommend;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecommendService {
    @Autowired
    private RecommendRepository recommendRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    public List<RecommendResponse> getAllRecommendWithParam(Optional<Long> userId, Optional<Long> boardId) {
        List<Recommend> list;
        if(userId.isPresent() && boardId.isPresent()){
            list = recommendRepository.findByUserIdAndBoardId(userId.get(),boardId.get());
        } else if (userId.isPresent()) {
            list = recommendRepository.findByUserId(userId.get());
        } else if (boardId.isPresent()) {
            list = recommendRepository.findByBoardId(boardId.get());
        }else
            list = recommendRepository.findAll();
        return list.stream().map(recommend -> new RecommendResponse(recommend)).collect(Collectors.toList());
    }



    public Recommend createOneRecommend(RecommendRequest request) {
        User user = userService.findById(request.getUserId());
        Board board = boardService.getOneBoardById(request.getBoardId());

        if (user != null && board != null) {
            Recommend recommendToSave = new Recommend();
            recommendToSave.setId(request.getId());
            recommendToSave.setBorad(board);
            recommendToSave.setUser(user);

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
