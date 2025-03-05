package com.thanhnt10.dodobook.apps.core.models.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenVerifyRequestDto {
    private String token;
}
