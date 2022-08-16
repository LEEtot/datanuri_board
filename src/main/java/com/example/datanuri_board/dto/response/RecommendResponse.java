package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Recommend;
import lombok.Data;

@Data
public class RecommendResponse {
    Long id;
    Long userId;
    Long boardId;

    public RecommendResponse(Recommend entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.boardId = entity.getBorad().getBoardId();
    }
}
