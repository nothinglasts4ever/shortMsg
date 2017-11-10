package com.smartbics.msgmailbox.rest.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data(staticConstructor = "of")
public class SendMessageRequest {
    private long from;
    private long to;
    private String message;
}