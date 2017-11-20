package com.smartbics.msgmailbox.rest;

import com.smartbics.msgmailbox.domain.Credentials;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.PersonRepository;
import com.smartbics.msgmailbox.service.CredentialsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
import javax.validation.ValidationException;

@RestController
public class PersonController {

    private PersonRepository personRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private CredentialsService credentialsService;

    public PersonController(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder, CredentialsService credentialsService) {
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.credentialsService = credentialsService;
    }

    @GetMapping("/users")
    public Iterable<Person> getAllUsers() {
        return personRepository.findAll();
    }

    @GetMapping("/users/current")
    public Person getCurrentUser() throws CredentialException {
        return credentialsService.getCurrentUser();
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