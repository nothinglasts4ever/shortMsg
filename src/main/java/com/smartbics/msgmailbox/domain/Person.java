package com.smartbics.msgmailbox.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data(staticConstructor = "of")
@Accessors(chain = true)
@ToString(of = {"firstName, lastName"})
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private Credentials credentials;
}