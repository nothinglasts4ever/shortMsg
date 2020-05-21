package com.smartbics.msgmailbox.repo;

import com.smartbics.msgmailbox.domain.Message;
import com.smartbics.msgmailbox.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByFromOrderByTimeStampDesc(Person from);
    List<Message> findByToOrderByTimeStampDesc(Person to);
    Message findByIdAndTo(long id, Person to);

}