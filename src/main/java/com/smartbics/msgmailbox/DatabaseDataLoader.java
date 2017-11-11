package com.smartbics.msgmailbox;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.MessageRepository;
import com.smartbics.msgmailbox.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private PersonRepository personRepository;
    private MessageRepository messageRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Person user1;
    private Person user2;
    private Person user3;
    private Person user4;
    private Person user5;
    private Person user6;

    public DatabaseDataLoader(PersonRepository personRepository, MessageRepository messageRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.messageRepository = messageRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createUsers();
        createMessages();
    }

    void createUsers() {
        user1 = Person.of().setFirstName("Homer").setLastName("Simpson").setMobileId("123").setPassword(bCryptPasswordEncoder.encode("secret1"));
        user2 = Person.of().setFirstName("Marge").setLastName("Simpson").setMobileId("465").setPassword(bCryptPasswordEncoder.encode("secret1"));
        user3 = Person.of().setFirstName("Bart").setLastName("Simpson").setMobileId("789").setPassword(bCryptPasswordEncoder.encode("secret1"));
        user4 = Person.of().setFirstName("Lisa").setLastName("Simpson").setMobileId("741").setPassword(bCryptPasswordEncoder.encode("secret1"));
        user5 = Person.of().setFirstName("Nelson").setLastName("Muntz").setMobileId("852").setPassword(bCryptPasswordEncoder.encode("haha"));
        user6 = Person.of().setFirstName("Ned").setLastName("Flanders").setMobileId("963").setPassword(bCryptPasswordEncoder.encode("okilydokily"));
        personRepository.save(user1);
        personRepository.save(user2);
        personRepository.save(user3);
        personRepository.save(user4);
        personRepository.save(user5);
        personRepository.save(user6);
    }

    void createMessages() {
        messageRepository.save(Message.of().setFrom(user1).setTo(user2).setMessage("D'oh!").setTimeStamp(LocalDateTime.now().minusWeeks(1)));
        messageRepository.save(Message.of().setFrom(user6).setTo(user1).setMessage("Hi-diddily-ho!").setTimeStamp(LocalDateTime.now().minusDays(3)));
        messageRepository.save(Message.of().setFrom(user5).setTo(user1).setMessage("Ha-ha!").setTimeStamp(LocalDateTime.now().minusDays(5)).setRead(true));
        messageRepository.save(Message.of().setFrom(user3).setTo(user1).setMessage("Eat my shorts!").setTimeStamp(LocalDateTime.now().minusMonths(2)));
        messageRepository.save(Message.of().setFrom(user1).setTo(user2).setMessage("Woo hoo!").setTimeStamp(LocalDateTime.now().minusWeeks(2)));
        messageRepository.save(Message.of().setFrom(user5).setTo(user2).setMessage("Ha-ha!").setTimeStamp(LocalDateTime.now().minusDays(1)));
    }

}