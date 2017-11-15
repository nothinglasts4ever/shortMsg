package com.smartbics.msgmailbox.rest.logging;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class ApiLoggingFilter extends AbstractRequestLoggingFilter {

    public ApiLoggingFilter() {
        super();
        setAfterMessagePrefix("API call [");
        setIncludeQueryString(true);
        setIncludePayload(true);
        setIncludeClientInfo(true);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }

}