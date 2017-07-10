package com.example.quotes.quotes.configuration;

import com.example.quotes.quotes.jwt.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Order(1)
@Configuration
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void configure(HttpSecurity httpSecurity) {
        httpSecurity.addFilterAfter(new AuthorizationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
    }

}
