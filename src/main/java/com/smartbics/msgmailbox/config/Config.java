package com.smartbics.msgmailbox.config;

import com.smartbics.msgmailbox.rest.logging.ApiLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Configuration
public class Config {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AbstractRequestLoggingFilter requestLoggingFilter() {
        return new ApiLoggingFilter();
    }

}