package com.hublotcloud.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hublotcloud.Constants;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    @SuppressWarnings("null")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constants.AUTHORIZATION);
        if (ObjectUtils.isEmpty(token)) {
            return false;
        }
        String backendToken = (String) request.getSession().getAttribute(Constants.TOKEN);
        if (ObjectUtils.isEmpty(backendToken) || !ObjectUtils.nullSafeEquals(token, backendToken)) {
            return false;
        }
        return true;
    }

}
