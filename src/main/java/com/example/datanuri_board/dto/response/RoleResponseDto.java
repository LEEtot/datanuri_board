package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Role;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {

    private String id;
    private String name;
    private String originName;
    private String description;

    public static RoleResponseDto of(Role role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, RoleResponseDto.class);
    }
}
