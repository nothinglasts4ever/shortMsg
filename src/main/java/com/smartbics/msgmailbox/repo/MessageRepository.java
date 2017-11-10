package com.smartbics.msgmailbox.repo;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByFromOrderByTimeStampDesc(User from);
    List<Message> findByToOrderByTimeStampDesc(User to);
}