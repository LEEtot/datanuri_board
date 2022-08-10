package com.example.datanuri_board.service;

import com.example.datanuri_board.entity.Board;
import com.example.datanuri_board.entity.User;

public interface RecommendService {

    boolean isLike(long boardId, long userId);

    boolean like(long userId, long boardId);

    void dislike(long userId, long boardId);
}
