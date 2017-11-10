package com.smartbics.msgmailbox.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data(staticConstructor = "of")
@Accessors(chain = true)
@ToString(exclude = "id")
public class Message implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User from;
    @ManyToOne
    private User to;
    private LocalDateTime timeStamp;
    private String message;
    private boolean read;
}