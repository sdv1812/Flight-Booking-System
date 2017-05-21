package com.crossover.techtrial.java.se.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.crossover.techtrial.java.se.security.domain.Account;
import com.crossover.techtrial.java.se.security.domain.User;
import com.crossover.techtrial.java.se.security.domain.UserCreateForm;


public interface UserService {
	
    public Optional<User> getUserById(long id);

    public Optional<User> getUserByEmail(String email);

    public Collection<User> getAllUsers();

    public User create(UserCreateForm form);
    
    public List<Account> getAllAccounts();
    
    
}
