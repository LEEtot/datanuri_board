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

    private String id;
    private String name;
    private String originName;
    private String description;

    public void setId(String id) {
        this.id = id;
    }
}
