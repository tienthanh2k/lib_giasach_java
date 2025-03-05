package com.thanhnt10.dodobook.common.enums;

public enum ResponseCode {
    SUCCESS("00", "Success"),
    INVALID_REQUEST("11", "Invalid request"),

    ERROR("99", "Error"),
    INTERNAL_ERROR("-1", "Internal Error"),

    ;

    private final String code;
    private final String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code() {
        return code;
    }

    public String description() {
        return description;
    }

    public static ResponseCode of(String code) {
        for (ResponseCode e : values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }
        return null;
    }
}
