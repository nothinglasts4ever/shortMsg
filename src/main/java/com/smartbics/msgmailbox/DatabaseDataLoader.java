package com.smartbics.msgmailbox;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.User;
import com.smartbics.msgmailbox.repo.MessageRepository;
import com.smartbics.msgmailbox.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createUsers();
        createMessages();
    }

    void createUsers() {
        user1 = User.of().setFirstName("Homer").setLastName("Simpson").setMobileId(123).setPassword("secret1");
        user2 = User.of().setFirstName("Marge").setLastName("Simpson").setMobileId(465).setPassword("secret1");
        user3 = User.of().setFirstName("Bart").setLastName("Simpson").setMobileId(789).setPassword("secret1");
        user4 = User.of().setFirstName("Lisa").setLastName("Simpson").setMobileId(741).setPassword("secret1");
        user5 = User.of().setFirstName("Nelson").setLastName("Muntz").setMobileId(852).setPassword("haha");
        user6 = User.of().setFirstName("Ned").setLastName("Flanders").setMobileId(963).setPassword("okilydokily");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
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