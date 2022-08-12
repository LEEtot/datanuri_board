package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.entity.User;
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
    private String phoneNumber;
    private LocalDateTime lastLoginTime;
    private String signUpApi;
    private String state;
    private String imgPath;
    private String creator;
    private LocalDateTime createdDate;
    private String modifier;
    private LocalDateTime modifiedDate;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.role = user.getRole();
        this.phoneNumber = user.getPhoneNumber();
        this.lastLoginTime = user.getLastLoginTime();
        this.signUpApi = user.getSignUpApi();
        this.state = user.getState();
        this.imgPath = user.getImgPath();
        this.creator = user.getCreator();
        this.createdDate = user.getCreatedDate();
        this.modifier = user.getModifier();
        this.modifiedDate = user.getModifiedDate();
    }
}
