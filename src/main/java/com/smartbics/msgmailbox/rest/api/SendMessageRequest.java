package com.smartbics.msgmailbox.rest.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data(staticConstructor = "of")
public class SendMessageRequest {
    private long recipient;
    private String message;
}