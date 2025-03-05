package com.thanhnt10.dodobook.apps.core.controller;

import com.thanhnt10.dodobook.apps.core.models.request.TokenClientCreateRequestDto;
import com.thanhnt10.dodobook.apps.core.models.request.TokenServerCreateRequestDto;
import com.thanhnt10.dodobook.apps.core.models.request.TokenVerifyRequestDto;
import com.thanhnt10.dodobook.apps.core.models.response.ClientSecretResponse;
import com.thanhnt10.dodobook.apps.core.models.response.TokenClientCreateResponseDto;
import com.thanhnt10.dodobook.apps.core.models.response.TokenServerCreateResponseDto;
import com.thanhnt10.dodobook.apps.core.models.response.TokenVerifyResponseDto;
import com.thanhnt10.dodobook.apps.core.service.AuthService;
import com.thanhnt10.dodobook.common.model.base.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/integration/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/generate-client-secret")
    public ClientSecretResponse generateKeys() {
        return authService.generateClientAndSecret();
    }

    @PostMapping("/generate-token-server")
    public Response<TokenServerCreateResponseDto> generateTokenServer(
            @RequestBody TokenServerCreateRequestDto request) {
        return authService.generateTokenServer(request);
    }

    @PostMapping("/generate-token-client")
    public Response<TokenClientCreateResponseDto> generateTokenClient(
            @RequestBody TokenClientCreateRequestDto request) {
        return authService.generateTokenClient(request);
    }

    @PostMapping("/verify-token")
    public Response<TokenVerifyResponseDto> verifyToken(
            @RequestBody TokenVerifyRequestDto request) {
        return authService.verifyToken(request);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getUserRoles() {
        // Lấy danh sách quyền từ Security Context
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        // Chuyển đổi danh sách quyền về List<String>
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(roles);
    }
}
