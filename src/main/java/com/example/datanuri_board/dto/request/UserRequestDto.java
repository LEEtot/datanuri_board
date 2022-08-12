package com.example.datanuri_board.dto.request;

import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String email;
    private String password;
    private String name;
    private String role;
    private String phoneNumber;
    private String signUpApi;
    private String state;
    private String imgPath;

    public User toEntity() {
        return User.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .name(this.getName())
                .role(this.getRole())
                .phoneNumber(this.getPhoneNumber())
                .signUpApi(this.getSignUpApi())
                .state(this.getState())
                .imgPath(this.getImgPath())
                .build();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
