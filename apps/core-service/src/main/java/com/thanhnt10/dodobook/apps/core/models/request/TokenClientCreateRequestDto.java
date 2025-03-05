package com.thanhnt10.dodobook.apps.core.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TokenClientCreateRequestDto {
    private String clientId;

    private String secretKey;

    private String email;

    private String userName;

    private String fullName;

    private String orgChartCode;

    private String ipAddress;

    private String userAgent;

    private String browserId;

    private List<String> roles;
}
