package com.crossover.techtrial.java.se.security.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the monetary_amount database table.
 * 
 */
@Entity
@Table(name="monetary_amount")
@NamedQuery(name="MonetaryAmount.findAll", query="SELECT m FROM MonetaryAmount m")
public class MonetaryAmount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	private Long amount;

	private String currency;

	//bi-directional one-to-one association to Account
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="account_id", referencedColumnName="id")
	@JsonIgnore
	private Account account;

	public MonetaryAmount() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "MonetaryAmount [id=" + id + ", amount=" + amount + ", currency=" + currency + ", account=" + account
				+ "]";
	}

}