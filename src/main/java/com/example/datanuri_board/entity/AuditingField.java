package com.example.datanuri_board.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingField {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false, updatable = false, length = 50)
    private String author; // 작성자
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime write_date; // 작성 일자
    @LastModifiedBy
    @Column(nullable = false, length = 50)
    private String modifier; // 수정자
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifier_date;  // 수정 일자

}
