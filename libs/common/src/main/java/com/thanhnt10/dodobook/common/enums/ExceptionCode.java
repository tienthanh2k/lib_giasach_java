package com.thanhnt10.dodobook.common.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionCode {

    ERR_SYS("ERR_SYS", "Có lỗi trong quá trình xử lý"),
    ERR_INVALID_TOKEN("ERR_INVALID_TOKEN", "Token không hợp lệ"),

    // START CHANNEL
    CHANNEL_NOT_FOUND("CHANNEL_001", "Không tìm thấy thông tin kênh tích hợp"),
    CHANNEL_EXISTED("CHANNEL_002", "Mã kênh tích hợp đã tồn tại"),
    // END CHANNEL

    // START TASK_TEMPLATE
    TASK_TEMPLATE_NOT_FOUND("TASK_TEMPLATE_001", "Không tìm thấy biểu mẫu"),
    TASK_TEMPLATE_EXISTED("TASK_TEMPLATE_002", "Mã biểu mẫu đã tồn tại"),
    TASK_TEMPLATE_USED("TASK_TEMPLATE_003", "Tồn tại biểu mẫu đã được sử dụng gán cho 1 tiến trình khác. Vui lòng kiểm tra lại!"),
    TASK_TEMPLATE_LOCKED("TASK_TEMPLATE_004", "Biểu mẫu đã ở trạng thái Khóa. Vui lòng kiểm tra lại!"),
    TASK_TEMPLATE_UNLOCKED("TASK_TEMPLATE_005", "Biểu mẫu đã ở trạng thái Hoạt động. Vui lòng kiểm tra lại!"),
    TASK_TEMPLATE_EXISTED_NAME("TASK_TEMPLATE_006", "Tên biểu mẫu đã tồn tại. Vui lòng kiểm tra lại"),
    TASK_TEMPLATE_REQUIRE_FUNCTION_URL("TASK_TEMPLATE_007", "URL chức năng bắt buộc nhập khi Kiểu xử lý công việc là Tự động"),
    TASK_TEMPLATE_INVALIDATE_URL("TASK_TEMPLATE_008", "URL chức năng không đúng định dạng"),
    // END TASK_TEMPLATE


    // START TASK_GROUP
    TASK_GROUP_NOT_FOUND("TASK_GROUP_001", "Không tìm thấy tiến trình"),
    TASK_GROUP_EXISTED("TASK_GROUP_002", "Mã tiến trình đã tồn tại"),
    // END TASK_GROUP

    ;

    private final String code;
    private final String message;

    ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static ExceptionCode of(String code) {
        for (ExceptionCode e : values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }
        return null;
    }
}
