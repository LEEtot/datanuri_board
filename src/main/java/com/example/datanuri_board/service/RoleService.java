package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.request.RoleRequestDto;
import com.example.datanuri_board.dto.request.SearchDto;
import com.example.datanuri_board.dto.response.RoleResponseDto;
import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Role 저장
     * @param role
     */
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    /**
     * Role 삭제
     * @param role
     */
    @Transactional
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    /**
     * Role 생성
     * @param roleRequestDto
     */
    @Transactional
    public void createRole(RoleRequestDto roleRequestDto) {

        Role role = Role.builder()
                .id(roleRequestDto.getId())
                .name(roleRequestDto.getName())
                .originName(roleRequestDto.getOriginName())
                .description(roleRequestDto.getDescription())
                .build();
        save(role);
    }

    /**
     * Role 수정
     * @param roleRequestDto
     */
    @Transactional
    public void updateRole(RoleRequestDto roleRequestDto) {
        Role role = findById(roleRequestDto.getId());
        String name = roleRequestDto.getName();
        String originName = roleRequestDto.getOriginName();
        String description = roleRequestDto.getDescription();
        if(role.getName() != name) {
            role.setName(name);
        }
        if(role.getOriginName() != originName) {
            role.setOriginName(originName);
        }
        if(role.getDescription() != description) {
            role.setDescription(description);
        }
    }

    /**
     * id 받아 Role 삭제
     * @param id
     */
    @Transactional
    public void deleteRole(String id) {
        Role role = findById(id);
        delete(role);
    }

    /**
     * 전체 조회 (다건 조회)
     * @return
     */
    public List<RoleResponseDto> findAll() {
        List<Role> roleList = roleRepository.findAll();
        List<RoleResponseDto> roleResponseDtoList = new ArrayList<>();
        for(Role role : roleList) {
            RoleResponseDto roleResponseDto = setRoleResponseDto(role);
            roleResponseDtoList.add(roleResponseDto);
        }
        return roleResponseDtoList;
    }

    /**
     * Role 검색 조회 (다건 조회)
     * @param searchDto
     * @return
     */
    public List<RoleResponseDto> findBySearch(SearchDto searchDto) {
        List<Role> roleList = new ArrayList<>();
        String orderCondition = searchDto.getOrderCondition();
        String selectCondition = searchDto.getSelectCondition();
        String searchCondition = searchDto.getSearchCondition();
        if(selectCondition == "all") {
            roleList = roleRepository.findByIdContainingOrNameContainingOrOriginNameContaining(searchCondition, searchCondition, searchCondition);
        } else if(selectCondition == "id") {
            roleList = roleRepository.findByIdContaining(searchCondition);
        } else if(selectCondition == "name") {
            roleList = roleRepository.findByNameContaining(searchCondition);
        } else if(selectCondition == "originName") {
            roleList = roleRepository.findByOriginNameContaining(searchCondition);
        } else {
        }
        List<RoleResponseDto> roleResponseDtoList = new ArrayList<>();
        for(Role role : roleList) {
            RoleResponseDto roleResponseDto = setRoleResponseDto(role);
            roleResponseDtoList.add(roleResponseDto);
        }
        return roleResponseDtoList;
    }

    /**
     * id로 권한 조회 (단건 조회)
     * @param id
     * @return
     */
    private Role findById(String id) {
        return roleRepository.findById(id).orElseThrow();
    }

    /**
     * id로 권한 조회 (단건 조회)
     * @param id
     * @return
     */
    public RoleResponseDto getRoleData(String id) {
        return setRoleResponseDto(findById(id));
    }

    /**
     * Role id 중복 검사
     * @param id
     * @return
     */
    public Boolean existsById(String id) {
        return roleRepository.existsById(id);
    }

    private RoleResponseDto setRoleResponseDto(Role role) {
        RoleResponseDto roleResponseDto = RoleResponseDto.builder()
                .id(role.getId())
                .name(role.getName())
                .originName(role.getOriginName())
                .description(role.getDescription())
                .build();
        return roleResponseDto;
    }
}
