package com.thanhnt10.dodobook.common.filter;

import com.thanhnt10.dodobook.common.util.CommonUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

import static com.thanhnt10.dodobook.common.contant.Constant.KEY.TRACE_ID;
import static com.thanhnt10.dodobook.common.contant.Constant.KEY.TRACE_ID_HEADER;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoggingTraceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String traceId = httpServletRequest.getHeader(TRACE_ID_HEADER);

        MDC.put(TRACE_ID, Objects.nonNull(traceId) ? traceId : CommonUtil.requestIdAtNow());
        try {
            if (isHealthCheckRequest(httpServletRequest)) {
                chain.doFilter(request, response);
                return;
            }
            log.info("Request to {} from {} with id: {}", httpServletRequest.getRequestURL(), httpServletRequest.getRemoteAddr(), traceId);

            String authorization = httpServletRequest.getHeader("Authorization");

            if (Objects.nonNull(authorization)) {
                String hashAuthorization = DigestUtils.md5Hex(authorization).toUpperCase();
                log.info("Authorization: {}", hashAuthorization);
            } else {
                log.info("Authorization is null");
            }

            long start = System.currentTimeMillis();
            chain.doFilter(request, response);
            int status = ((HttpServletResponse) response).getStatus();
            log.info("Response {status={}, process_time={}ms, url={}}",
                    status, System.currentTimeMillis() - start, httpServletRequest.getRequestURL());
        } finally {
            MDC.clear();
        }
    }
    private boolean isHealthCheckRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("/health");
    }
}
