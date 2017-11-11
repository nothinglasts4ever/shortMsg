package com.smartbics.msgmailbox.auth;

import com.smartbics.msgmailbox.domain.Credentials;
import com.smartbics.msgmailbox.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.smartbics.msgmailbox.domain.Person;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MsgMailboxUserDetailService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String mobileId) throws UsernameNotFoundException, BadCredentialsException {
        Person person = personRepository.findByCredentials_MobileId(mobileId);
        if (person == null) {
            throw new UsernameNotFoundException(mobileId);
        }
        Credentials credentials = person.getCredentials();
        if (credentials == null) {
            throw new BadCredentialsException(mobileId);
        }
        return new User(credentials.getMobileId(), credentials.getPassword(), Collections.emptyList());
    }

}