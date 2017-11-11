package com.smartbics.msgmailbox.rest;

import com.smartbics.msgmailbox.domain.Credentials;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.PersonRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

@RestController
public class PersonController {

    private PersonRepository personRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonController(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/users")
    public Iterable<Person> getAllUsers() {
        return personRepository.findAll();
    }

    @PostMapping("/users/register")
    public void signUp(@RequestBody Person person) {
        Credentials credentials = person.getCredentials();
        if (credentials == null) throw new ValidationException("Credentials were not provided");

        credentials.setPassword(bCryptPasswordEncoder.encode(credentials.getPassword()));
        person.setCredentials(credentials);
        personRepository.save(person);
    }

}