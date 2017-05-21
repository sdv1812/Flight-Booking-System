package com.crossover.techtrial.java.se.security.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	//bi-directional one-to-one association to User
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	@JsonIgnore
	private User user;

	//bi-directional one-to-one association to MonetaryAmount
	@OneToOne(mappedBy="account", fetch=FetchType.LAZY)
	private MonetaryAmount balance;

	public Account() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MonetaryAmount getBalance() {
		return this.balance;
	}

	public void setMonetaryAmount(MonetaryAmount balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", user=" + user + ", balance=" + balance + "]";
	}

}