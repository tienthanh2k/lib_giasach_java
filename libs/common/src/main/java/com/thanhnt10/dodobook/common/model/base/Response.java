package com.thanhnt10.dodobook.common.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.thanhnt10.dodobook.common.enums.ExceptionCode;
import com.thanhnt10.dodobook.common.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private String code;
    private String message;
    private T data;

    public static <R> Response<R> ok(R data) {
        return Response.<R>builder()
                .code(ResponseCode.SUCCESS.code())
                .message(ResponseCode.SUCCESS.description())
                .data(data)
                .build();
    }

    public static <R> Response<R> of(ResponseCode responseCode) {
        return Response.<R>builder().code(responseCode.code()).message(responseCode.description()).build();
    }

    public static <R> Response<R> of(ExceptionCode exceptionCode) {
        return Response.<R>builder().code(exceptionCode.code()).message(exceptionCode.message()).build();
    }

    public static <R> Response<R> error(String errorMessage) {
        return Response.<R>builder().code(ResponseCode.ERROR.code()).message(errorMessage).build();
    }

    @JsonIgnore
    public boolean isSuccess() {
        return ResponseCode.SUCCESS.code().equals(code);
    }

    @JsonIgnore
    public boolean isNotSuccess() {
        return !isSuccess();
    }
}