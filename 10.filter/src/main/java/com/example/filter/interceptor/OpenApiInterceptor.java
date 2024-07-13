package com.example.filter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class OpenApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("pre handle");
        // controller 전달, false면 전달x

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        OpenApi methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class);
        if(methodLevel != null){
            log.info("method level");
            return true;
        }

        OpenApi classLevel = handlerMethod.getBeanType().getAnnotation(OpenApi.class);
        if(classLevel != null) {
            log.info("class level");
            return true;
        }

        log.info("open api 아닙니다 : {}", request.getRequestURI());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("post handle");
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("after completion");
        //        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
