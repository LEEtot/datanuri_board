package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    List<Role> findByIdContainingOrNameContainingOrOriginNameContaining(String id, String name, String originName);

    List<Role> findByIdContaining(String id);

    List<Role> findByNameContaining(String name);

    List<Role> findByOriginNameContaining(String originName);
}
