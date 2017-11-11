package com.smartbics.msgmailbox.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data(staticConstructor = "of")
@Accessors(chain = true)
@ToString(exclude = "id")
public class Message implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Person from;
    @ManyToOne
    private Person to;
    private LocalDateTime timeStamp;
    private String message;
    private boolean read;
}