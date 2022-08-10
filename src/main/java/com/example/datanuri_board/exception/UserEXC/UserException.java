package com.example.datanuri_board.exception.UserEXC;

import com.example.datanuri_board.exception.BaseException;
import com.example.datanuri_board.exception.BaseExceptionType;

public class UserException extends BaseException {

    private UserExceptionType exceptionType;

    public UserException(BaseExceptionType exceptionType) {
        this.exceptionType = (UserExceptionType) exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
