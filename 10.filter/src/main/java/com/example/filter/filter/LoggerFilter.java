package com.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 진입 전
        log.info(">>>>> 진입");

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        String reqJson = new String(req.getContentAsByteArray());
        log.info("req : {}", reqJson);

        String resJson = new String(res.getContentAsByteArray());
        log.info("res : {}", resJson);

        log.info("<<<<< 리턴");
        // 진입 후

        // 실행하지 않으면 응답 결과를 볼 수 없음
        res.copyBodyToResponse();
    }
}
