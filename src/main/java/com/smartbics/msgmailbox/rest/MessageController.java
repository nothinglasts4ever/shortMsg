package com.smartbics.msgmailbox.rest;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.rest.api.SendMessageRequest;
import com.smartbics.msgmailbox.service.MessageService;
import com.smartbics.msgmailbox.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final PersonService personService;

    @GetMapping("/inbox")
    public List<Message> getInboxMessages() throws CredentialException {
        Person currentUser = personService.getCurrentUser();
        return messageService.getInboxMessages(currentUser);
    }

    @GetMapping("/outbox")
    public List<Message> getOutboxMessages() throws CredentialException {
        Person currentUser = personService.getCurrentUser();
        return messageService.getOutboxMessages(currentUser);
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestBody SendMessageRequest request) throws CredentialException {
        Person currentUser = personService.getCurrentUser();
        long recipientId = request.getRecipient();
        if (recipientId == currentUser.getId()) throw new ValidationException("Sender and recipient should be different persons");

        return messageService.save(request, currentUser);
    }

    @PostMapping("/mark-as-read")
    public Message markMessageAsRead(@RequestParam long messageId) throws CredentialException {
        Person currentUser = personService.getCurrentUser();
        return messageService.markMessageAsRead(messageId, currentUser);
    }

}