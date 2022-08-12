package com.example.datanuri_board.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter @Setter /** 클래스 내 모든 필드의 Getter/Setter 메소드 생성 */
@NoArgsConstructor /** 기본 생성자를 자동으로 추가(접근권한은 protected로 제한) */
@AllArgsConstructor /** 모든 필드 값을 파라미터로 받는 생성자를 추가 */
@Builder
@Table(name = "boardUser")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    private String role;

    @NotEmpty
    private String phoneNumber;

    private LocalDateTime lastLoginTime;

    @NotEmpty
    private String signUpApi;

    @NotEmpty
    @Size(min = 4, max = 4)
    private String state;

    private String imgPath;

    public User update(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        return this;
    }

}

