package com.thanhnt10.dodobook.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhnt10.dodobook.common.enums.ErrorCode;
import com.thanhnt10.dodobook.common.enums.ExceptionCode;
import com.thanhnt10.dodobook.common.exception.BusinessException;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenUtil {
    public static Map<String, Object> getPayload(String token) {
        token.replace("Bearer ", "");
        // Tách lấy phần payload (phần ở giữa)
        String[] chunks = token.split("\\.");
        if (chunks.length < 2) {
            return null;
        }

        // Decode Base64
        String payloadJson = new String(Base64.getUrlDecoder().decode(chunks[1]));

        // Chuyển JSON sang Map
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> payloadMap = objectMapper.readValue(payloadJson, Map.class);
            return payloadMap;
        }
        catch (JsonProcessingException e) {
            throw new BusinessException(ErrorCode.ERR_INVALID_TOKEN);
        }
    }

    public static List<String> convertObjectToList(Object rolesObject) {
        if (rolesObject instanceof List<?>) {
            return ((List<?>) rolesObject).stream()
                    .filter(item -> item instanceof String) // Đảm bảo item là String
                    .map(Object::toString) // Convert về String
                    .collect(Collectors.toList());
        }
        return List.of(); // Trả về danh sách rỗng nếu không đúng định dạng
    }

}
