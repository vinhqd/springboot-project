package com.example.interceptors;

import com.example.dto.MyUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SystemInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            myUser = (MyUser) authentication.getPrincipal();
        }
        request.setAttribute("myUser", myUser);
        return true;
    }
}
