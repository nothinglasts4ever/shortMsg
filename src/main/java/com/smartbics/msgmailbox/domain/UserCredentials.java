package com.smartbics.msgmailbox.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class UserCredentials {
    private String mobileId;
    private String password;
}