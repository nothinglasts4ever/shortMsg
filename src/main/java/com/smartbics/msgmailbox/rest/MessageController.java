package com.smartbics.msgmailbox.rest;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.User;
import com.smartbics.msgmailbox.repo.MessageRepository;
import com.smartbics.msgmailbox.repo.UserRepository;
import com.smartbics.msgmailbox.rest.api.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/messages/inbox")
    public Iterable<Message> getInboxMessages(@RequestParam long id) {
        User user = userRepository.findOne(id);
        return messageRepository.findByToOrderByTimeStampDesc(user);
    }

    @GetMapping("/messages/outbox")
    public Iterable<Message> getOutboxMessages(@RequestParam long id) {
        User user = userRepository.findOne(id);
        return messageRepository.findByFromOrderByTimeStampDesc(user);
    }

    @PostMapping("/messages/send")
    public Message sendMessage(@RequestBody SendMessageRequest request) {
        long senderId = request.getFrom();
        long recipientId = request.getTo();
        if (senderId == recipientId) {
            // throw exception
        }

        User from = userRepository.findOne(senderId);
        User to = userRepository.findOne(recipientId);
        if (from == null || to == null) {
            // throw exception
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