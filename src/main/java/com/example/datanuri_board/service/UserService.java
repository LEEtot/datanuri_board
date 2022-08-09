package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.request.SearchDto;
import com.example.datanuri_board.dto.response.UserResponseDto;
import com.example.datanuri_board.dto.request.UserRequestDto;
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
     * @param userRequestDto
     */
    @Transactional
    public void signup(UserRequestDto userRequestDto) {
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .name(userRequestDto.getName())
                .role(userRequestDto.getRole())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .signUpApi(userRequestDto.getSignUpApi())
                .state(userRequestDto.getState())
                .imgPath(userRequestDto.getImgPath())
                .build();
        save(user);
    }

    /**
     * 회원 탈퇴
     * @param id
     */
    @Transactional
    public void withdrawal(Long id) {
        stateUpdate(id, "withdrawal");
    }

    /**
     * 상태 변경(Activate, Block, Withdrawal)
     * @param id
     * @param state
     */
    @Transactional
    public void stateUpdate(Long id, String state) {
        User user = findById(id);
        user.setState(state);
    }

    /**
     * 권한 변경
     * @param id
     * @param role
     */
    @Transactional
    public void roleUpdate(Long id, String role) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(role);
    }

    /**
     * 사용자 정보 수정
     * @param userUpdateRequestDto
     */
    @Transactional
    public void update(Long id, UserRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id).orElseThrow();
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
     * id로 User 조회 (단건 조회)
     * @param id
     * @return
     */
    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    /**
     * 회원 정보 조회
     * @param id
     * @return
     */
    public UserResponseDto getUserData(Long id) {
        return setUserResponseDto(findById(id));
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
     * 검색 (다건 조회)
     * @param searchDto
     * @return
     */
    public List<UserResponseDto> findBySearch(SearchDto searchDto) {
        List<User> userList = new ArrayList<>();
        String orderCondition = searchDto.getOrderCondition();
        String selectCondition = searchDto.getSelectCondition();
        String searchCondition = searchDto.getSearchCondition();
        if(selectCondition == "all") {
            userList = userRepository.findByEmailContainingOrNameContaining(searchCondition, searchCondition);
        } else if(selectCondition == "name") {
            userList = userRepository.findByNameContaining(searchCondition);
        } else if(selectCondition == "email") {
            userList = userRepository.findByEmailContaining(searchCondition);
        } else {
        }
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
