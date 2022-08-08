package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.UserResponseDto;
import com.example.datanuri_board.dto.UserSignupRequestDto;
import com.example.datanuri_board.dto.UserUpdateRequestDto;
import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /**
     * User 저장
     * @param user
     */
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * User 삭제
     * @param user
     */
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    /**
     * 회원 가입
     * @param userSignupRequestDto
     */
    @Transactional
    public void signup(UserSignupRequestDto userSignupRequestDto) {
        User user = User.builder()
                .email(userSignupRequestDto.getEmail())
                .password(userSignupRequestDto.getPassword())
                .name(userSignupRequestDto.getName())
                .role(userSignupRequestDto.getRole())
                .phoneNumber(userSignupRequestDto.getPhoneNumber())
                .signUpApi(userSignupRequestDto.getSignUpApi())
                .state(userSignupRequestDto.getState())
                .imgPath(userSignupRequestDto.getImgPath())
                .build();
        save(user);
    }

    /**
     * 상태 변경(Activate, Block, Withdrawal)
     * @param id
     * @param state
     */
    @Transactional
    public void stateUpdate(Long id, String state) {
        User user = userRepository.findById(id).orElseThrow();
        user.setState(state);
    }

    /**
     * 권한 변경
     * @param id
     * @param role
     */
    @Transactional
    public void roleUpdate(Long id, Role role) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(role);
    }

    /**
     * 사용자 정보 수정
     * @param userUpdateRequestDto
     */
    @Transactional
    public void update(UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userUpdateRequestDto.getId()).orElseThrow();
        int phoneNumber = userUpdateRequestDto.getPhoneNumber();
        String imgPath = userUpdateRequestDto.getImgPath();
        if(user.getPhoneNumber() != phoneNumber) {
            user.setPhoneNumber(phoneNumber);
        }
        if(user.getImgPath() != imgPath) {
            user.setImgPath(imgPath);
        }
    }

    /**
     * id로 User 조회(단건 조회)
     * @param id
     * @return
     */
    public UserResponseDto findById(Long id) {
        return setUserResponseDto(userRepository.findById(id).orElseThrow());
    }

    /**
     * email 중복 검사
     * @param email
     * @return
     */
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * User 전체 조회(다건 조회)
     * @return
     */
    public List<UserResponseDto> findAll() {
        List<UserResponseDto> userResponseList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for(User user : userList) {
            userResponseList.add(setUserResponseDto(user));
        }
        return userResponseList;
    }

    /**
     * 검색(select - 전체)(다건 조회)
     * @param email
     * @param name
     * @return
     */
    public List<UserResponseDto> findByEmailContainingOrNameContaining(String email, String name) {
        List<User> userList = userRepository.findByEmailContainingOrNameContaining(email, name);
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for(User user : userList) {
            userResponseDtoList.add(setUserResponseDto(user));
        }
        return userResponseDtoList;
    }

    /**
     * 검색(select - email)(다건 조회)
     * @param email
     * @return
     */
    public List<UserResponseDto> findByEmailContaining(String email) {
        List<User> userList = userRepository.findByEmailContaining(email);
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for(User user : userList) {
            userResponseDtoList.add(setUserResponseDto(user));
        }
        return userResponseDtoList;
    }

    /**
     * 검색(select - name)(다건 조회)
     * @param name
     * @return
     */
    public List<UserResponseDto> findByNameContaining(String name) {
        List<User> userList = userRepository.findByNameContaining(name);
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for(User user : userList) {
            userResponseDtoList.add(setUserResponseDto(user));
        }
        return userResponseDtoList;
    }

    /**
     * User 데이터를 UserResponseDto에 적재
     * @param user
     * @return
     */
    private UserResponseDto setUserResponseDto(User user) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .lastLoginTime(user.getLastLoginTime())
                .signUpApi(user.getSignUpApi())
                .state(user.getState())
                .imgPath(user.getImgPath())
                .creator(user.getCreator())
                .createdDate(user.getCreatedDate())
                .modifier(user.getModifier())
                .modifiedDate(user.getModifiedDate())
                .build();
        return userResponseDto;
    }
}
