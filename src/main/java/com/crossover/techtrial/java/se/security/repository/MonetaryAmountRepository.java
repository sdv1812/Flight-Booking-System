package com.crossover.techtrial.java.se.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crossover.techtrial.java.se.security.domain.MonetaryAmount;


public interface MonetaryAmountRepository extends JpaRepository<MonetaryAmount, Long>{
	
}
