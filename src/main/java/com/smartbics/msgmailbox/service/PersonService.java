package com.smartbics.msgmailbox.service;

import com.smartbics.msgmailbox.domain.Credentials;
import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.CredentialException;
import javax.validation.ValidationException;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public Collection<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person getCurrentUser() throws CredentialException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mobileId = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            mobileId = authentication.getName();
        }
        if (mobileId == null) throw new CredentialException("Cannot find current user");

        return personRepository.findByCredentials_MobileId(mobileId)
                .orElseThrow(() -> new CredentialException("Cannot find current user"));
    }

    @Transactional
    public void save(Person person) {
        Credentials credentials = person.getCredentials();
        if (credentials == null) throw new ValidationException("Credentials were not provided");

        credentials.setPassword(bCryptPasswordEncoder.encode(credentials.getPassword()));
        person.setCredentials(credentials);
        personRepository.save(person);
    }

}