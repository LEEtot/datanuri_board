package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Recommend;
import lombok.Data;

@Data
public class RecommendResponse {
    Long id;
    Long userId;
    Long boardId;


    /**
     * 변수 명 수정 (주의 부탁드립니다!)
     * .getBorad() -> .getBoard()
     */
    public RecommendResponse(Recommend entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.boardId = entity.getBoard().getBoardId();
    }
}
