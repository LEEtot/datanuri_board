package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.request.RoleRequestDto;
import com.example.datanuri_board.dto.request.SearchDto;
import com.example.datanuri_board.dto.response.RoleResponseDto;
import com.example.datanuri_board.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    /**
     * 검색 조건 반영해서 Role 목록 조회 (다건 조회)
     * @param params
     * @return
     */
    @GetMapping("/")
    public List<RoleResponseDto> roleSearch(@RequestParam Map<String, String> params) {
        return roleService.findBySearch(params);
    }

    /**
     * 권한 조회 (단건 조회)
     * @param roleId
     * @return
     */
    @GetMapping("/{roleId}/data")
    public RoleResponseDto getRoleData(@PathVariable("roleId") String roleId) {
        return roleService.getRoleData(roleId);
    }

    /**
     * 권한 id 중복 검사
     * @param id
     * @return
     */
    @GetMapping("/duplicateCheck")
    public Boolean duplicateCheck(@RequestParam("id") String id) {
        return roleService.existsById(id);
    }

    /**
     * 권한 생성
     * @param roleRequestDto
     */
    @PostMapping("/create")
    public void createRole(@RequestBody RoleRequestDto roleRequestDto) {
        if(roleService.createRole(roleRequestDto)) {
            log.info("권한생성성공");
        } else {
            log.info("권한생성실패");
        }
    }

    /**
     * 권한 수정
     * @param roleRequestDto
     */
    @PostMapping("/{roleId}/update")
    public void updateRole(@PathVariable("roleId") String roleId, @RequestBody RoleRequestDto roleRequestDto) {
        roleService.updateRole(roleId, roleRequestDto);
    }

    /**
     * 권한 삭제
     * @param roleId
     */
    @PostMapping("/{roleId}/delete")
    public void deleteRole(@PathVariable("roleId") String roleId) {
        roleService.deleteRole(roleId);
    }
}
