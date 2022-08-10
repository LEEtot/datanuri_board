package com.example.datanuri_board.service;

import com.example.datanuri_board.entity.Recommend;
import com.example.datanuri_board.repository.RecommendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendRepository recommendRepository;


    @Override
    public boolean isLike(long boardId, long memberId) {
        return recommendRepository.findByBoardIdAndUserId(boardId, memberId)
                .isPresent();
    }

    @Override
    public boolean like(long userId, long boardId) {
        Recommend recommend = Recommend.builder()
                .userId(userId)
                .boardId(boardId)
                .build();
        try {
            recommendRepository.save(recommend);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
        return true;
    }

    @Override
    public void dislike(long userId, long boardId) {
        Optional<Recommend> recommend = recommendRepository.findByBoardIdAndUserId(boardId, userId);
        recommend.ifPresent(recommendRepository::delete);
    }

}
