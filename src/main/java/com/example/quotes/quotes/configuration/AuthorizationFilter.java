package com.example.quotes.quotes.configuration;

import com.example.quotes.quotes.exception.AuthorizationHeaderNotFoundException;
import com.example.quotes.quotes.exception.AuthorizationHeaderValueException;
import com.example.quotes.quotes.jwt.TokenService;
import com.example.quotes.quotes.util.TokenProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    more here -> http://www.baeldung.com/spring-security-custom-filter
 */
@Slf4j
public class AuthorizationFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenService tokenService;

    @Autowired
    public AuthorizationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        try {
            String authorizationHeaderValue = getHeaderValue(httpServletRequest);
            String token = TokenProcessor.getTokenValueFromHeaderString(authorizationHeaderValue).trim();

            log.info(token.trim());
            tokenService.createToken();

        } catch (AuthorizationHeaderNotFoundException | AuthorizationHeaderValueException e) {
            log.error("Error:", e);
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getHeaderValue(HttpServletRequest httpServletRequest) {
        String headerValue = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(headerValue)) {
            throw new AuthorizationHeaderNotFoundException("Authorization header not found");
        }
        return headerValue;
    }

}