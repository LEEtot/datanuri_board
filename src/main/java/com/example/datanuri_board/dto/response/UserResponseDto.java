package com.example.datanuri_board.dto.response;

import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.entity.User;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter @Setter
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

    public static UserResponseDto of(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserResponseDto.class);

//        return UserResponseDto.builder()
//                .id(user.getId())
//                .email(user.getEmail())
//                .name(user.getName())
//                .role(user.getName())
//                .phoneNumber(user.getPhoneNumber())
//                .lastLoginTime(user.getLastLoginTime())
//                .signUpApi(user.getSignUpApi())
//                .state(user.getState())
//                .imgPath(user.getImgPath())
//                .creator(user.getCreator())
//                .createdDate(user.getCreatedDate())
//                .modifier(user.getModifier())
//                .modifiedDate(user.getModifiedDate())
//                .build();
    }
}
