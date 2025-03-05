package com.thanhnt10.dodobook.apps.core.service.impl;

import com.thanhnt10.dodobook.apps.core.models.request.TokenClientCreateRequestDto;
import com.thanhnt10.dodobook.apps.core.models.request.TokenServerCreateRequestDto;
import com.thanhnt10.dodobook.apps.core.models.request.TokenVerifyRequestDto;
import com.thanhnt10.dodobook.apps.core.models.response.ClientSecretResponse;
import com.thanhnt10.dodobook.apps.core.models.response.TokenClientCreateResponseDto;
import com.thanhnt10.dodobook.apps.core.models.response.TokenServerCreateResponseDto;
import com.thanhnt10.dodobook.apps.core.models.response.TokenVerifyResponseDto;
import com.thanhnt10.dodobook.apps.core.service.AuthService;
import com.thanhnt10.dodobook.common.contant.Constant;
import com.thanhnt10.dodobook.common.model.base.Response;
import com.thanhnt10.dodobook.common.util.CommonUtil;
import com.thanhnt10.dodobook.common.util.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.thanhnt10.dodobook.common.contant.Constant.CLIENT_TOKEN_CLAIMS;
import static com.thanhnt10.dodobook.common.contant.Constant.SERVER_TOKEN_CLAIMS;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Value("${setting.client-token-expired-hour:12}") // 12 tiếng
    private long clientTokenExpiredHour;

    @Value("${setting.server-token-expired-hour:168}")  // 7 ngày
    private long serverTokenExpiredHour;

    @Value("${setting.secretKey}")  // 7 ngày
    private String secretKey;

    public AuthServiceImpl() {
    }

    @Override
    public ClientSecretResponse generateClientAndSecret() {
        String clientId = UUID.randomUUID().toString();
        String secretKey = generateSecretKey();

        return ClientSecretResponse.builder()
                .clientId(clientId)
                .secretKey(secretKey)
                .build();
    }

    @Override
    public Response<TokenServerCreateResponseDto> generateTokenServer(TokenServerCreateRequestDto request) {

        long expirationTime = serverTokenExpiredHour * 1000 * 60 * 60; // 1 giờ
        SecretKey secretKey = generateSecretKey(request.getSecretKey());

        String token = Jwts.builder()
                .subject(request.getChannel())
                .claim(Constant.SERVER_TOKEN_CLAIMS.CHANNEL, request.getChannel())
                .claim(Constant.SERVER_TOKEN_CLAIMS.IP_ADDRESS, request.getIpAddress())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey) // HMAC with SHA-256
                .compact();

        var data = TokenServerCreateResponseDto.builder()
                .token(token)
                .build();
        return Response.<TokenServerCreateResponseDto>builder()
                .data(data)
                .build();
    }

    @Override
    public Response<TokenClientCreateResponseDto> generateTokenClient(TokenClientCreateRequestDto request) {

        long expirationTime = clientTokenExpiredHour * 1000 * 60 * 60; // 1 giờ
        SecretKey secretKey = generateSecretKey(request.getSecretKey());
        String token = Jwts.builder()
                .subject("123")
                .claim(CLIENT_TOKEN_CLAIMS.CHANNEL, "123")
                .claim(CLIENT_TOKEN_CLAIMS.EMAIL, request.getEmail())
                .claim(CLIENT_TOKEN_CLAIMS.USER_NAME, request.getUserName())
                .claim(CLIENT_TOKEN_CLAIMS.FULL_NAME, request.getFullName())
                .claim(CLIENT_TOKEN_CLAIMS.ORG_CHART_CODE, request.getOrgChartCode())
                .claim(CLIENT_TOKEN_CLAIMS.IP_ADDRESS, request.getIpAddress())
                .claim(CLIENT_TOKEN_CLAIMS.USER_AGENT, request.getUserAgent())
                .claim(CLIENT_TOKEN_CLAIMS.BROWSER_ID, request.getBrowserId())
                .claim(CLIENT_TOKEN_CLAIMS.ROLES, request.getRoles())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey) // HMAC with SHA-256
                .compact();

        var data = TokenClientCreateResponseDto.builder()
                .token(token)
                .build();
        return Response.<TokenClientCreateResponseDto>builder()
                .data(data)
                .build();
    }

    @Override
    public Response<TokenVerifyResponseDto> verifyToken(TokenVerifyRequestDto request) {
        if(CommonUtil.isNullOrEmpty(request.getToken())){
            log.error("token is empty");
            return Response.ok(
                    TokenVerifyResponseDto.builder()
                            .success(false)
                            .build()
            );
        }

        Map<String, Object> payload = TokenUtil.getPayload(request.getToken());
        if(payload != null && !payload.containsKey(SERVER_TOKEN_CLAIMS.CHANNEL)) {
            log.error("claim channel in token is not found");
            return Response.ok(
                    TokenVerifyResponseDto.builder()
                            .success(false)
                            .build()
            );
        }
        String code = (String) payload.get(SERVER_TOKEN_CLAIMS.CHANNEL);

        return Response.ok(
                TokenVerifyResponseDto.builder()
                        .success(verifyToken(request.getToken(), secretKey))
                        .build()
        );
    }

    private boolean verifyToken(String token, String sercetKey) {
        try {
            Jwts
                .parser()
                .verifyWith(generateSecretKey(sercetKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("Expired_JWT_TOKEN");
        } catch (UnsupportedJwtException e) {
            log.warn("INVALID_JWT_TOKEN");
        }
        catch (Exception e){
        }
        return false;
    }

    private String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32]; // 256-bit key
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    private SecretKey generateSecretKey(String secret) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }
}
