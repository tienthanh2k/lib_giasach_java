package com.thanhnt10.dodobook.apps.core.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TokenClientCreateResponseDto {
    private String token;
}
