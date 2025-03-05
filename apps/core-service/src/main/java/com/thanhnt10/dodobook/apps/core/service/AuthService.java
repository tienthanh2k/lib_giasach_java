package com.thanhnt10.dodobook.apps.core.service;


import com.thanhnt10.dodobook.apps.core.models.request.TokenClientCreateRequestDto;
import com.thanhnt10.dodobook.apps.core.models.request.TokenServerCreateRequestDto;
import com.thanhnt10.dodobook.apps.core.models.request.TokenVerifyRequestDto;
import com.thanhnt10.dodobook.apps.core.models.response.ClientSecretResponse;
import com.thanhnt10.dodobook.apps.core.models.response.TokenClientCreateResponseDto;
import com.thanhnt10.dodobook.apps.core.models.response.TokenServerCreateResponseDto;
import com.thanhnt10.dodobook.apps.core.models.response.TokenVerifyResponseDto;
import com.thanhnt10.dodobook.common.model.base.Response;

public interface AuthService {
    ClientSecretResponse generateClientAndSecret();

    Response<TokenServerCreateResponseDto> generateTokenServer(TokenServerCreateRequestDto request);

    Response<TokenClientCreateResponseDto> generateTokenClient(TokenClientCreateRequestDto request);

    Response<TokenVerifyResponseDto> verifyToken(TokenVerifyRequestDto request);
}
