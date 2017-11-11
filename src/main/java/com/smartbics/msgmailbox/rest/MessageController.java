package com.smartbics.msgmailbox.rest;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.MessageRepository;
import com.smartbics.msgmailbox.repo.PersonRepository;
import com.smartbics.msgmailbox.rest.api.SendMessageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.time.LocalDateTime;

@RestController
public class MessageController {

    private MessageRepository messageRepository;
    private PersonRepository personRepository;

    public MessageController(MessageRepository messageRepository, PersonRepository personRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/messages/inbox")
    public Iterable<Message> getInboxMessages(@RequestParam long id) {
        Person user = personRepository.findOne(id);
        return messageRepository.findByToOrderByTimeStampDesc(user);
    }

    @GetMapping("/messages/outbox")
    public Iterable<Message> getOutboxMessages(@RequestParam long id) {
        Person user = personRepository.findOne(id);
        return messageRepository.findByFromOrderByTimeStampDesc(user);
    }

    @PostMapping("/messages/send")
    public Message sendMessage(@RequestBody SendMessageRequest request) {
        long senderId = request.getFrom();
        long recipientId = request.getTo();
        if (senderId == recipientId) {
            throw new ValidationException("Sender and recipient should be different persons");
        }

        Person from = personRepository.findOne(senderId);
        Person to = personRepository.findOne(recipientId);
        if (from == null || to == null) {
            throw new ValidationException("Sender or recipient cannot be found");
        }

        Message message = Message.of()
                .setMessage(request.getMessage())
                .setFrom(from)
                .setTo(to)
                .setTimeStamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @PostMapping("/messages/mark-as-read")
    public Message markMessageAsRead(@RequestParam long messageId) {
        Message message = messageRepository.findOne(messageId);
        if (message != null && !message.isRead()) {
            message.setRead(true);
            messageRepository.save(message);
        }
        return message;
    }

}