package com.crossover.techtrial.java.se.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crossover.techtrial.java.se.security.domain.User;


public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByEmail(String email);
}
