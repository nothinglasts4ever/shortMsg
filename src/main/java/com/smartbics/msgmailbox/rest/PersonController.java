package com.smartbics.msgmailbox.rest;

import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.PersonRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private PersonRepository personRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonController(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/users")
    public Iterable<Person> getAllUsers(@RequestParam int id) {
        // filter by id
        // encapsulate login data
        return personRepository.findAll();
    }

   /* @PostMapping("/sign-up")
    public void signUp(@RequestBody Person user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        personRepository.save(user);
    }*/

}