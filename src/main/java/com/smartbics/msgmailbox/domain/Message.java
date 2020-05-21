package com.smartbics.msgmailbox.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.OffsetDateTime;

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
    private OffsetDateTime timeStamp;
    private String message;
    private boolean read;
}