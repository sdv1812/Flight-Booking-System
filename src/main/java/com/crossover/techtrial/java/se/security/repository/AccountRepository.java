package com.crossover.techtrial.java.se.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crossover.techtrial.java.se.security.domain.Account;
import com.crossover.techtrial.java.se.security.domain.User;

public interface AccountRepository extends JpaRepository<Account, String> {
	public Optional<Account> findByUser(User user);

}
