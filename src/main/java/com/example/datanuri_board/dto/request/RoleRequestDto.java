package com.example.datanuri_board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequestDto {

    private String order_condition;
    private String search_condition;
//    private int list_count;
}
