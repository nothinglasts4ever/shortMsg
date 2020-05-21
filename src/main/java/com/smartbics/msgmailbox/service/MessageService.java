package com.smartbics.msgmailbox.service;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.MessageRepository;
import com.smartbics.msgmailbox.repo.PersonRepository;
import com.smartbics.msgmailbox.rest.api.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<Message> getInboxMessages(Person currentUser) {
        return messageRepository.findByToOrderByTimeStampDesc(currentUser);
    }

    @Transactional(readOnly = true)
    public List<Message> getOutboxMessages(Person currentUser) {
        return messageRepository.findByFromOrderByTimeStampDesc(currentUser);
    }

    @Transactional
    public Message markMessageAsRead(long messageId, Person currentUser) {
        Message message = messageRepository.findByIdAndTo(messageId, currentUser);
        if (message != null && !message.isRead()) {
            message.setRead(true);
            messageRepository.save(message);
        }
        return message;
    }

    @Transactional
    public Message save(SendMessageRequest request, Person currentUser) {
        Person to = personRepository.findOne(request.getRecipient());
        if (to == null) throw new ValidationException("Recipient cannot be found");

        Message message = Message.of()
                .setMessage(request.getMessage())
                .setFrom(currentUser)
                .setTo(to)
                .setTimeStamp(OffsetDateTime.now());
        return messageRepository.save(message);
    }

}