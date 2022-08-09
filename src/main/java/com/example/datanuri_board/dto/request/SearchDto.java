package com.example.datanuri_board.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {

    private String orderCondition;
    private String selectCondition;
    private String searchCondition;
}
