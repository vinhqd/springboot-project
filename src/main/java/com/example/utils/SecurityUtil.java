package com.example.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityUtil {

    public static List<String> getAuthorities() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
        List<String> result = authorities.stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());
        return result;
    }

}
