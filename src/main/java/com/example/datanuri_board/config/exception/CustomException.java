package com.example.datanuri_board.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** ErrorCode에 직접 정의한 Custom 예외를 처리할 Exception 클래스 */
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;
}
