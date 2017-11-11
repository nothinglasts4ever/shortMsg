package com.smartbics.msgmailbox.repo;

import com.smartbics.msgmailbox.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByMobileId(String mobileId);
}