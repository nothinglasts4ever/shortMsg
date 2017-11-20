package com.smartbics.msgmailbox.service;

import com.smartbics.msgmailbox.domain.Person;
import com.smartbics.msgmailbox.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
public class CredentialsService {

    @Autowired
    private PersonRepository personRepository;

    public Person getCurrentUser() throws CredentialException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mobileId = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            mobileId = authentication.getName();
        }
        if (mobileId == null) throw new CredentialException("Cannot find current user");
        return personRepository.findByCredentials_MobileId(mobileId);
    }

}