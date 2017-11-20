package com.smartbics.msgmailbox.rest;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.MessageRepository;
import com.smartbics.msgmailbox.repo.PersonRepository;
import com.smartbics.msgmailbox.rest.api.SendMessageRequest;
import com.smartbics.msgmailbox.service.CredentialsService;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;

@RestController
public class MessageController {

    private MessageRepository messageRepository;
    private PersonRepository personRepository;
    private CredentialsService credentialsService;

    public MessageController(MessageRepository messageRepository, PersonRepository personRepository, CredentialsService credentialsService) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
        this.credentialsService = credentialsService;
    }

    @GetMapping("/messages/inbox")
    public Iterable<Message> getInboxMessages() throws CredentialException {
        Person currentUser = credentialsService.getCurrentUser();
        return messageRepository.findByToOrderByTimeStampDesc(currentUser);
    }

    @GetMapping("/messages/outbox")
    public Iterable<Message> getOutboxMessages() throws CredentialException {
        Person currentUser = credentialsService.getCurrentUser();
        return messageRepository.findByFromOrderByTimeStampDesc(currentUser);
    }

    @PostMapping("/messages/send")
    public Message sendMessage(@RequestBody SendMessageRequest request) throws CredentialException {
        Person currentUser = credentialsService.getCurrentUser();
        long recipientId = request.getRecipient();
        if (currentUser.getId() == recipientId) throw new ValidationException("Sender and recipient should be different persons");

        Person to = personRepository.findOne(recipientId);
        if (to == null) throw new ValidationException("Recipient cannot be found");

        Message message = Message.of()
                .setMessage(request.getMessage())
                .setFrom(currentUser)
                .setTo(to)
                .setTimeStamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @PostMapping("/messages/mark-as-read")
    public Message markMessageAsRead(@RequestParam long messageId) throws CredentialException {
        Person currentUser = credentialsService.getCurrentUser();
        Message message = messageRepository.findByIdAndTo(messageId, currentUser);
        if (message != null && !message.isRead()) {
            message.setRead(true);
            messageRepository.save(message);
        }
        return message;
    }

}