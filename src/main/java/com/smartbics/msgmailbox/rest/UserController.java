package com.smartbics.msgmailbox.rest;

import com.smartbics.msgmailbox.domain.User;
import com.smartbics.msgmailbox.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getAllUsers(@RequestParam int id) {
        // filter by id
        // encapsulate login data
        return userRepository.findAll();
    }

}