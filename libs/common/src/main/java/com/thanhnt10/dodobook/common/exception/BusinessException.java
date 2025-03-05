package com.thanhnt10.dodobook.common.exception;

import com.thanhnt10.dodobook.common.enums.ErrorCode;

public class BusinessException extends RuntimeException{
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
