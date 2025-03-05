package com.thanhnt10.dodobook.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    ERR_INVALID_TOKEN("ERR_INVALID_TOKEN", "Token không hợp lệ", HttpStatus.FORBIDDEN),
    UNCATEGORIZED_EXCEPTION("9999", "Uncategorized error",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY("1001", "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED("1002", "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID("1003", "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD("1004", "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED("1005", "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED("1006", "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("1007", "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB("1008", "Your age must be at least {min}", HttpStatus.BAD_REQUEST),

    // START AUTHOR
    AUTHOR_NOT_FOUND("CHANNEL_001", "Không tìm thấy thông tin Tác giả", HttpStatus.BAD_REQUEST),
    AUTHOR_EXISTED("CHANNEL_002", "Tên tác giả đã tồn tại", HttpStatus.BAD_REQUEST),
    // END CHANNEL

    // START CHANNEL
    CHANNEL_NOT_FOUND("CHANNEL_001", "Không tìm thấy thông tin kênh tích hợp", HttpStatus.BAD_REQUEST),
    CHANNEL_EXISTED("CHANNEL_002", "Mã kênh tích hợp đã tồn tại", HttpStatus.BAD_REQUEST),
    // END CHANNEL

    // START TASK_TEMPLATE
    TASK_TEMPLATE_NOT_FOUND("TASK_TEMPLATE_001", "Không tìm thấy biểu mẫu", HttpStatus.BAD_REQUEST),
    TASK_TEMPLATE_EXISTED("TASK_TEMPLATE_002", "Mã biểu mẫu đã tồn tại", HttpStatus.BAD_REQUEST),
    TASK_TEMPLATE_USED("TASK_TEMPLATE_003", "Tồn tại biểu mẫu đã được sử dụng gán cho 1 tiến trình khác. Vui lòng kiểm tra lại!", HttpStatus.BAD_REQUEST),
    TASK_TEMPLATE_LOCKED("TASK_TEMPLATE_004", "Biểu mẫu đã ở trạng thái Khóa. Vui lòng kiểm tra lại!", HttpStatus.BAD_REQUEST),
    TASK_TEMPLATE_UNLOCKED("TASK_TEMPLATE_005", "Biểu mẫu đã ở trạng thái Hoạt động. Vui lòng kiểm tra lại!", HttpStatus.BAD_REQUEST),
    TASK_TEMPLATE_EXISTED_NAME("TASK_TEMPLATE_006", "Tên biểu mẫu đã tồn tại. Vui lòng kiểm tra lại", HttpStatus.BAD_REQUEST),
    TASK_TEMPLATE_REQUIRE_FUNCTION_URL("TASK_TEMPLATE_007", "URL chức năng bắt buộc nhập khi Kiểu xử lý công việc là Tự động", HttpStatus.BAD_REQUEST),
    TASK_TEMPLATE_INVALIDATE_URL("TASK_TEMPLATE_008", "URL chức năng không đúng định dạng", HttpStatus.BAD_REQUEST),
    // END TASK_TEMPLATE

    // START TASK_GROUP
    TASK_GROUP_NOT_FOUND("TASK_GROUP_001", "Không tìm thấy tiến trình", HttpStatus.BAD_REQUEST),
    TASK_GROUP_EXISTED("TASK_GROUP_002", "Mã tiến trình đã tồn tại", HttpStatus.BAD_REQUEST),
        // END TASK_GROUP
    ;

    ErrorCode(String code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final String code;
    private final String message;
    private final HttpStatusCode statusCode;
}
