package com.smartbics.msgmailbox.repo;

import com.smartbics.msgmailbox.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}