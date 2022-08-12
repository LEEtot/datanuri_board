package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.request.RoleRequestDto;
import com.example.datanuri_board.dto.request.SearchDto;
import com.example.datanuri_board.dto.response.RoleResponseDto;
import com.example.datanuri_board.entity.Role;
import com.example.datanuri_board.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Role 생성
     * @param roleRequestDto
     */
    @Transactional
    public Boolean createRole(RoleRequestDto roleRequestDto) {
        if(!existsById(roleRequestDto.getId())) {
            roleRepository.save(roleRequestDto.toEntity());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Role 수정
     * @param roleRequestDto
     */
    @Transactional
    public void updateRole(String id, RoleRequestDto roleRequestDto) {
        Role role = roleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 권한이 없습니다."));
        String name = roleRequestDto.getName();
        String originName = roleRequestDto.getOriginName();
        String description = roleRequestDto.getDescription();
        role.setName(name);
        role.setOriginName(originName);
        role.setDescription(description);
    }

    /**
     * id 받아 Role 삭제
     * @param id
     */
    @Transactional
    public void deleteRole(String id) {
        Role role = roleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 권한이 없습니다."));
        roleRepository.delete(role);
    }

    /**
     * 전체 조회 (다건 조회)
     * @return
     */
    public List<RoleResponseDto> findAll() {
        return roleRepository
                .findAll()
                .stream()
                .map(RoleResponseDto::of)
                .collect(Collectors.toList());
    }

    /**
     * Role 검색 조회 (다건 조회)
     * @param params
     * @return
     */
    public List<RoleResponseDto> findBySearch(Map<String, String> params) {
        String orderCondition = params.get("orderCondition");
        String selectCondition = params.get("selectCondition");
        String searchCondition = params.get("searchCodition");
        if(selectCondition == "all") {
            return roleRepository
                    .findByIdContainingOrNameContainingOrOriginNameContaining(searchCondition, searchCondition, searchCondition)
                    .stream()
                    .map(RoleResponseDto::of)
                    .collect(Collectors.toList());
        } else if(selectCondition == "id") {
            return roleRepository
                    .findByIdContaining(searchCondition)
                    .stream()
                    .map(RoleResponseDto::of)
                    .collect(Collectors.toList());
        } else if(selectCondition == "name") {
            return roleRepository
                    .findByNameContaining(searchCondition)
                    .stream()
                    .map(RoleResponseDto::of)
                    .collect(Collectors.toList());
        } else if(selectCondition == "originName") {
            return roleRepository
                    .findByOriginNameContaining(searchCondition)
                    .stream()
                    .map(RoleResponseDto::of)
                    .collect(Collectors.toList());
        } else {
            log.info("Select Condition Error");
            return roleRepository
                    .findAll()
                    .stream()
                    .map(RoleResponseDto::of)
                    .collect(Collectors.toList());
        }
    }

    /**
     * id로 권한 조회 (단건 조회)
     * @param id
     * @return
     */
    public RoleResponseDto getRoleData(String id) {
        return RoleResponseDto.of(roleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 권한이 없습니다.")));
    }

    /**
     * Role id 중복 검사
     * @param id
     * @return
     */
    public Boolean existsById(String id) {
        return roleRepository.existsById(id);
    }
}
