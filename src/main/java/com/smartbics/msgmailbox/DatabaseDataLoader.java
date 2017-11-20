package com.smartbics.msgmailbox;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.domain.Credentials;
import com.smartbics.msgmailbox.repo.MessageRepository;
import com.smartbics.msgmailbox.repo.PersonRepository;
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

    private Person person1;
    private Person person2;
    private Person person3;
    private Person person4;
    private Person person5;
    private Person person6;

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
        Credentials credentials1 = Credentials.of().setMobileId("Duff123").setPassword(bCryptPasswordEncoder.encode("secret1"));
        Credentials credentials2 = Credentials.of().setMobileId("Margery456").setPassword(bCryptPasswordEncoder.encode("secret2"));
        Credentials credentials3 = Credentials.of().setMobileId("ElBarto789").setPassword(bCryptPasswordEncoder.encode("secret3"));
        Credentials credentials4 = Credentials.of().setMobileId("741").setPassword(bCryptPasswordEncoder.encode("secret1"));
        Credentials credentials5 = Credentials.of().setMobileId("852").setPassword(bCryptPasswordEncoder.encode("haha"));
        Credentials credentials6 = Credentials.of().setMobileId("963").setPassword(bCryptPasswordEncoder.encode("okilydokily"));
        person1 = Person.of().setFirstName("Homer").setLastName("Simpson").setCredentials(credentials1);
        person2 = Person.of().setFirstName("Marge").setLastName("Simpson").setCredentials(credentials2);
        person3 = Person.of().setFirstName("Bart").setLastName("Simpson").setCredentials(credentials3);
        person4 = Person.of().setFirstName("Lisa").setLastName("Simpson").setCredentials(credentials4);
        person5 = Person.of().setFirstName("Nelson").setLastName("Muntz").setCredentials(credentials5);
        person6 = Person.of().setFirstName("Ned").setLastName("Flanders").setCredentials(credentials6);
        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);
        personRepository.save(person5);
        personRepository.save(person6);
    }

    void createMessages() {
        messageRepository.save(Message.of().setFrom(person1).setTo(person2).setMessage("D'oh!").setTimeStamp(LocalDateTime.now().minusWeeks(1)));
        messageRepository.save(Message.of().setFrom(person6).setTo(person1).setMessage("Hi-diddily-ho!").setTimeStamp(LocalDateTime.now().minusDays(3)));
        messageRepository.save(Message.of().setFrom(person5).setTo(person1).setMessage("Ha-ha!").setTimeStamp(LocalDateTime.now().minusDays(5)).setRead(true));
        messageRepository.save(Message.of().setFrom(person3).setTo(person1).setMessage("Eat my shorts!").setTimeStamp(LocalDateTime.now().minusMonths(2)));
        messageRepository.save(Message.of().setFrom(person1).setTo(person2).setMessage("Woo hoo!").setTimeStamp(LocalDateTime.now().minusWeeks(2)));
        messageRepository.save(Message.of().setFrom(person5).setTo(person2).setMessage("Ha-ha!").setTimeStamp(LocalDateTime.now().minusDays(1)));
    }

}