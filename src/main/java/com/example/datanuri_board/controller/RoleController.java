package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.request.RoleRequestDto;
import com.example.datanuri_board.dto.request.SearchDto;
import com.example.datanuri_board.dto.response.RoleResponseDto;
import com.example.datanuri_board.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    /**
     * 검색 조건 반영해서 Role 목록 조회 (다건 조회)
     * @param searchDto
     * @return
     */
    @GetMapping("/")
    public List<RoleResponseDto> roleSearch(@RequestBody SearchDto searchDto) {
        return roleService.findBySearch(searchDto);
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
     * @param roleRequestDto
     * @return
     */
    @GetMapping("/duplicateCheck")
    public Boolean duplicateCheck(@RequestBody RoleRequestDto roleRequestDto) {
        return roleService.existsById(roleRequestDto.getId());
    }

    /**
     * 권한 생성
     * @param roleRequestDto
     */
    @PostMapping("/create")
    public void createRole(@RequestBody RoleRequestDto roleRequestDto) {
        roleService.createRole(roleRequestDto);
    }

    /**
     * 권한 수정
     * @param roleRequestDto
     */
    @PostMapping("/{roleId}/update")
    public void updateRole(@PathVariable("roleId") String roleId, @RequestBody RoleRequestDto roleRequestDto) {
        roleRequestDto.setId(roleId);
        roleService.updateRole(roleRequestDto);
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
