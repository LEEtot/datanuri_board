package com.example.datanuri_board.service;

import com.example.datanuri_board.config.SecurityUtil;
import com.example.datanuri_board.dto.request.SearchDto;
import com.example.datanuri_board.dto.response.UserResponseDto;
import com.example.datanuri_board.dto.request.UserRequestDto;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.repository.UserRepository;
import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     * @param userRequestDto
     */
    @Transactional
    public Boolean signup(UserRequestDto userRequestDto) {
        if(!existsByEmail(userRequestDto.getEmail())) {
            userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            userRepository.save(userRequestDto.toEntity());
            return true;
        } else {
            return false;
        }
    }

    /**
     * 회원 탈퇴
     * @param id
     */
    @Transactional
    public void withdrawal(Long id) {
        stateUpdate(id, "S003");
    }

    /**
     * 상태 변경(Activate, Block, Withdrawal)
     * @param id
     * @param state
     */
    @Transactional
    public void stateUpdate(Long id, String state) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));
        user.setState(state);
    }

    /**
     * 권한 변경
     * @param id
     * @param role
     */
    @Transactional
    public void roleUpdate(Long id, String role) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다"));
        user.setRole(role);
    }

    /**
     * 사용자 정보 수정
     * @param userUpdateRequestDto
     */
    @Transactional
    public void update(Long id, UserRequestDto userUpdateRequestDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다"));
        String phoneNumber = userUpdateRequestDto.getPhoneNumber();
        String imgPath = userUpdateRequestDto.getImgPath();
        user.setPhoneNumber(phoneNumber);
        user.setImgPath(imgPath);
    }

    /**
     * 회원 정보 조회
     * @param id
     * @return
     */
    public UserResponseDto getUserData(Long id) {
        return UserResponseDto.of(userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다")));
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
        return userRepository
                .findAll()
                .stream()
                .map(UserResponseDto::of)
                .collect(Collectors.toList());
    }

    /**
     * 검색 (다건 조회)
     * @param params
     * @return
     */
    public List<UserResponseDto> findBySearch(Map<String, String> params) {
        String orderCondition = params.get("orderCondition");
        String selectCondition = params.get("selectCondition");
        String searchCondition = params.get("searchCondition");
        if(selectCondition.equals("all")) {
            return userRepository
                    .findByEmailContainingOrNameContaining(searchCondition, searchCondition)
                    .stream()
                    .map(UserResponseDto::of)
                    .collect(Collectors.toList());
        } else if(selectCondition.equals("name")) {
            return userRepository
                    .findByNameContaining(searchCondition)
                    .stream()
                    .map(UserResponseDto::of)
                    .collect(Collectors.toList());
        } else if(selectCondition.equals("email")) {
            return userRepository
                    .findByEmailContaining(searchCondition)
                    .stream()
                    .map(UserResponseDto::of)
                    .collect(Collectors.toList());
        } else {
            return userRepository
                    .findAll()
                    .stream()
                    .map(UserResponseDto::of)
                    .collect(Collectors.toList());
        }
    }

    public UserResponseDto getMyInfoBySecurity() {
        return userRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(UserResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }
}
