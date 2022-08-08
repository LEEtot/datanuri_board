package com.example.datanuri_board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter /** 클래스 내 모든 필드의 Getter/Setter 메소드 생성 */
@NoArgsConstructor
/** 기본 생성자를 자동으로 추가(접근권한은 protected로 제한) */
@AllArgsConstructor
/** 모든 필드 값을 파라미터로 받는 생성자를 추가 */
@Builder
@Table(name = "role")
public class Role extends BaseEntity {

    @Id
    @Size(min = 4, max = 4)
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String originName;

    private String description;
}
