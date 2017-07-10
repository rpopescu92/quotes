package com.example.quotes.quotes.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenService {

    private final Environment environment;

    @Autowired
    public TokenService(Environment environment) {
        this.environment = environment;
    }

    public String createToken() {
        final String secret = environment.getProperty("quotes.jwt.secret");
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Secret:" + environment.getProperty("quotes.jwt.secret"));
        return "";
    }

}
