package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.TokenDto;
import com.example.datanuri_board.dto.request.SearchDto;
import com.example.datanuri_board.dto.request.UserRequestDto;
import com.example.datanuri_board.dto.response.UserResponseDto;
import com.example.datanuri_board.service.AuthService;
import com.example.datanuri_board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    /**
     * 검색 조건 반영해서 회원 목록 조회 (다건 조회)
     * @param params
     * @return
     */
    @GetMapping("/")
    public List<UserResponseDto> userSearch(@RequestParam Map<String, String> params) {
        log.info(params.toString());
        return userService.findBySearch(params);
    }
//    @GetMapping("/")
//    public List<UserResponseDto> userSearch(@RequestParam("orderCondition") String orderCondition, @RequestParam("selectCondition") String selectCondition, @RequestParam("searchCondition") String searchCondition) {
//        log.info(orderCondition + selectCondition + searchCondition);
//        return userService.findBySearch(orderCondition, selectCondition, searchCondition);
//    }

    /**
     * 회원 정보 조회 (단건 조회)
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/data")
    public UserResponseDto getUserData(@PathVariable("userId") Long userId) {
        return userService.getUserData(userId);
    }

    /**
     * email 중복 검사
     * @param email
     * @return
     */
    @GetMapping("/duplicateCheck")
    public Boolean duplicateCheck(@RequestParam("email") String email) {
        return userService.existsByEmail(email);
    }

    /**
     * 회원 가입
     * @param userRequestDto
     */
    @PostMapping("/signup")
    public void signupUser(@RequestBody UserRequestDto userRequestDto) {
        if(userService.signup(userRequestDto)) {
            log.info("회원가입성공");
        } else {
            log.info("회원가입실패");
        }
    }

    /**
     * 회원 정보 수정
     * @param userRequestDto
     * @param userId
     */
    @PostMapping("/{userId}/update")
    public void updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable("userId") Long userId) {
        userService.update(userId, userRequestDto);
    }

    /**
     * 회원 탈퇴
     * @param userId
     */
    @PostMapping("/{userId}/delete")
    public void withdrawalUser(@PathVariable("userId") Long userId) {
        userService.withdrawal(userId);
    }

    /**
     * 권한 수정
     * @param userId
     * @param userRequestDto
     */
    @PostMapping("/{userId}/roleUpdate")
    public void roleUpdate(@PathVariable("userId") Long userId, @RequestBody UserRequestDto userRequestDto) {
        userService.roleUpdate(userId, userRequestDto.getRole());
    }

    /**
     * 상태 수정 (block 처리)
     * @param userId
     * @param userRequestDto
     */
    @PostMapping("/{userId}/stateUpdate")
    public void stateUpdate(@PathVariable("userId") Long userId, @RequestBody UserRequestDto userRequestDto) {
        userService.stateUpdate(userId, userRequestDto.getState());
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserRequestDto userRequestDto) {
        return authService.login(userRequestDto);
    }

    @GetMapping("/me")
    public UserResponseDto me(){
        return userService.getMyInfoBySecurity();
    }
}
