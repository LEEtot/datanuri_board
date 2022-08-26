package com.example.datanuri_board.dto.request;

import com.example.datanuri_board.entity.Role;
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

    public Role toEntity() {
        return Role.builder()
                .id(this.getId())
                .name(this.getName())
                .originName(this.getOriginName())
                .description(this.getDescription())
                .build();
    }
}
