package com.application.databaseapplication_v01.config;

import com.application.databaseapplication_v01.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
        if (currentUser.hasRole("ROLE_STUDENT")) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/students/dashboard");
        }
        else if (currentUser.hasRole("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/users");
        }
        else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
        }
    }
}
