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
public class UserRequestDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;
    private int phoneNumber;
    private String signUpApi;
    private String state;
    private String imgPath;
}
