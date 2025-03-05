package com.thanhnt10.dodobook.apps.core.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenServerCreateRequestDto {
    private String clientId;
    private String secretKey;
    private String channel;
    private String ipAddress;
}
