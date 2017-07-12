package com.example.quotes.quotes.configuration;

import com.example.quotes.quotes.jwt.TokenService;
import com.example.quotes.quotes.services.QuotesUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private QuotesUserDetailsService userDetailsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new QuotesAuthenticationProvider());
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] patterns = new String[]{
                "/api/login",
                "/api/register"
        };

        http
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //this will ensure that SpringSecurity won't save any authentication date
                .and()
                .authorizeRequests()
                .antMatchers(patterns)
                .permitAll()
                .antMatchers("/api/**")
                .authenticated()
                .and()
                .csrf()
                .disable();
        http.addFilterBefore(new AuthorizationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
    }

}
