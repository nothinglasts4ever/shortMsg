package com.smartbics.msgmailbox.rest;

import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public Collection<Person> getAllUsers() {
        return personService.findAll();
    }

    @GetMapping("/current")
    public Person getCurrentUser() throws CredentialException {
        return personService.getCurrentUser();
    }

    @PostMapping("/register")
    public void signUp(@RequestBody Person person) {
        personService.save(person);
    }

}