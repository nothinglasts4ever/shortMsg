package com.smartbics.msgmailbox.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data(staticConstructor = "of")
@Accessors(chain = true)
@ToString(of = {"firstName, lastName"})
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private long mobileId;
    private String password;
}