package com.tujuhsembilan.talentcenter.util.jwt;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tujuhsembilan.talentcenter.response.MessageResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint{

    @Autowired
    private MessageSource messageSource;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
        throws IOException, ServletException{
        
        String message = messageSource.getMessage("unauthorize", null, Locale.getDefault());
        MessageResponse messageResponse = new MessageResponse(message, HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application.json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(messageResponse));
    }
}
