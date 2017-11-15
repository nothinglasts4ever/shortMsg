package com.smartbics.msgmailbox;

import com.smartbics.msgmailbox.rest.logging.ApiLoggingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@SpringBootApplication
public class MsgMailboxApplication {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AbstractRequestLoggingFilter requestLoggingFilter() {
        return new ApiLoggingFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(MsgMailboxApplication.class, args);
    }

}