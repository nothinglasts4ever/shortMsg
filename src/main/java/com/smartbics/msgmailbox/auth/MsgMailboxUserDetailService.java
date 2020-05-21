package com.smartbics.msgmailbox.auth;

import com.smartbics.msgmailbox.domain.Credentials;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MsgMailboxUserDetailService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String mobileId) throws UsernameNotFoundException, BadCredentialsException {
        Person person = personRepository.findByCredentials_MobileId(mobileId)
                .orElseThrow(() -> new UsernameNotFoundException(mobileId));

        Credentials credentials = person.getCredentials();
        if (credentials == null) throw new BadCredentialsException(mobileId);

        return new User(credentials.getMobileId(), credentials.getPassword(), Collections.emptyList());
    }

}