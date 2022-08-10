package com.example.datanuri_board.exception.commentEXC;

import com.example.datanuri_board.exception.BaseException;
import com.example.datanuri_board.exception.BaseExceptionType;

public class CommentException extends BaseException {
    private BaseExceptionType baseExceptionType;


    public CommentException(BaseExceptionType baseExceptionType) {
        this.baseExceptionType = baseExceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return this.baseExceptionType;
    }
}