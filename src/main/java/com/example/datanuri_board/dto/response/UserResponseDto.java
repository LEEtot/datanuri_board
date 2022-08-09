package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String email;
    private String name;
    private String role;
    private int phoneNumber;
    private LocalDateTime lastLoginTime;
    private String signUpApi;
    private String state;
    private String imgPath;
    private String creator;
    private LocalDateTime createdDate;
    private String modifier;
    private LocalDateTime modifiedDate;
}
