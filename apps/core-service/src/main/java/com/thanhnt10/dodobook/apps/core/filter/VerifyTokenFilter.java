package com.thanhnt10.dodobook.apps.core.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhnt10.dodobook.apps.core.models.request.TokenVerifyRequestDto;
import com.thanhnt10.dodobook.apps.core.service.AuthService;
import com.thanhnt10.dodobook.common.contant.Constant;
import com.thanhnt10.dodobook.common.util.CommonUtil;
import com.thanhnt10.dodobook.common.util.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class VerifyTokenFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private final String[] PUBLIC_ENDPOINTS = {
            "/swagger-ui",
            "/v3/api-docs",
            "/actuator",
            "/api/integration/auth"
    };
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AuthService authService;

    public VerifyTokenFilter(
            AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            if (isPublicEndpoint(request.getRequestURI())) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = getToken(request);

            if (CommonUtil.isNullOrEmpty(token)) {
                // Tạo một object response JSON
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("error", "Unauthorized");
                responseBody.put("message", "Invalid Token");
                responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);

                // Set content type và status
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                // Ghi response dưới dạng JSON
                response.getWriter().write(objectMapper.writeValueAsString(responseBody));
                return;
            }

            TokenVerifyRequestDto verifyRequestDto = TokenVerifyRequestDto.builder()
                    .token(token)
                    .build();
            var verifyResponse = authService.verifyToken(verifyRequestDto);
            if (verifyResponse.getData().isSuccess()) {
                log.info("Token verification success");
                // Fake để pass UsernamePasswordAuthenticationFilter, do không dùng user
                // Xác thực qua VerifyTokenFilter
                var payload = TokenUtil.getPayload(token);
                var roles = TokenUtil.convertObjectToList(payload.get(Constant.CLIENT_TOKEN_CLAIMS.ROLES));
                UserDetails userDetails = new User("abc", "", convertRolesToAuthorities(roles));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                return;
            } else {
                // Tạo một object response JSON
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("error", "Unauthorized");
                responseBody.put("message", "Invalid Token");
                responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);

                // Set content type và status
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                // Ghi response dưới dạng JSON
                response.getWriter().write(objectMapper.writeValueAsString(responseBody));
                return;
            }
        } catch (Exception exp) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = response.getWriter();
            writer.print(exp.getMessage());
            writer.flush();
            writer.close();
        }

        filterChain.doFilter(request, response);
    }
    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean isPublicEndpoint(String endpoint) {
        return Arrays.stream(PUBLIC_ENDPOINTS).anyMatch(endpoint::startsWith);
    }

    public Collection<? extends GrantedAuthority> convertRolesToAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new) // Chuyển mỗi role thành GrantedAuthority
                .collect(Collectors.toList());
    }
}

