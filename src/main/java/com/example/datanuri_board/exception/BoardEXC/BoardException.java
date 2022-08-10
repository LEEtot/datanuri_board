package com.example.datanuri_board.exception.BoardEXC;

import com.example.datanuri_board.exception.BaseException;
import com.example.datanuri_board.exception.BaseExceptionType;

public class BoardException extends BaseException {

    private BaseExceptionType baseExceptionType;

    public BoardException(BaseExceptionType baseExceptionType){
        this.baseExceptionType = baseExceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return this.baseExceptionType;
    }
}
