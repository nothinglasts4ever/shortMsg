package com.smartbics.msgmailbox.repo;

import com.smartbics.msgmailbox.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByCredentials_MobileId(String mobileId);
    Collection<Person> findAll();
}