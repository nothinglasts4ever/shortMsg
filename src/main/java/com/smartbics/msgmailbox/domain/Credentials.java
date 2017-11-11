package com.smartbics.msgmailbox.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
@Data(staticConstructor = "of")
@Accessors(chain = true)
@ToString(exclude = {"mobileId", "password"})
public class Credentials {
    private String mobileId;
    private String password;
}