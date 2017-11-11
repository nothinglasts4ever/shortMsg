package com.smartbics.msgmailbox.auth;

import com.smartbics.msgmailbox.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.smartbics.msgmailbox.domain.Person;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class MsgMailboxUserDetailService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String mobileId) throws UsernameNotFoundException {
        Person person = personRepository.findByMobileId(mobileId);
        if (person == null) {
            throw new UsernameNotFoundException(mobileId);
        }
        return new User(person.getMobileId(), person.getPassword(), emptyList());
    }

}