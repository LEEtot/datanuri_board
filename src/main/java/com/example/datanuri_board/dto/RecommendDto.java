package com.example.datanuri_board.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendDto {
    private String userId;
    private String boardId;
}
