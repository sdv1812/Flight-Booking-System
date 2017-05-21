package com.crossover.techtrial.java.se.security.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crossover.techtrial.java.se.application.util.JsonUtil;
import com.crossover.techtrial.java.se.application.util.RestTemplateUtil;
import com.crossover.techtrial.java.se.application.util.Settings;
import com.crossover.techtrial.java.se.application.util.URL;
import com.crossover.techtrial.java.se.gamma.domain.AirlineTicket;
import com.crossover.techtrial.java.se.security.domain.CurrencyT;
import com.crossover.techtrial.java.se.security.domain.Deposit;
import com.crossover.techtrial.java.se.security.domain.MonetaryAmount;
import com.crossover.techtrial.java.se.security.domain.Account;
import com.crossover.techtrial.java.se.security.domain.Role;
import com.crossover.techtrial.java.se.security.domain.User;
import com.crossover.techtrial.java.se.security.domain.UserCreateForm;
import com.crossover.techtrial.java.se.security.repository.AccountRepository;
import com.crossover.techtrial.java.se.security.repository.MonetaryAmountRepository;
import com.crossover.techtrial.java.se.security.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
	private final MonetaryAmountRepository monetaryAmountRepository;
	private final RestTemplate restTemplate;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RestTemplateBuilder restTemplateBuilder, AccountRepository accountRepository, MonetaryAmountRepository monetaryAmountRepository) {
		this.userRepository = userRepository;
		this.restTemplate = restTemplateBuilder.build();
		this.accountRepository = accountRepository;
		this.monetaryAmountRepository = monetaryAmountRepository;
	}
	
	@Override
	public Optional<User> getUserById(long id) {
		LOGGER.debug("Getting user={}", id);
		return Optional.ofNullable(userRepository.findOne(id));
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
		return userRepository.findByEmail(email);
	}

	@Override
	public Collection<User> getAllUsers() {
		LOGGER.debug("Getting all users");
		return userRepository.findAll(new Sort("email"));
	}

	@Override
	public User create(UserCreateForm form) {
		User user = new User();
		user.setEmail(form.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
		user.setRole(Role.User);
		Account account = createPaypalletAccount();
		Account updatedAccount = depositDefaultAmount(account.getId());
		user.setAccount(updatedAccount);
		LOGGER.info("Balance after update " + updatedAccount);
		updatedAccount.setUser(user);
		MonetaryAmount monetaryAmount = updatedAccount.getBalance();
		monetaryAmount.setAccount(updatedAccount);
		return monetaryAmountRepository.save(updatedAccount.getBalance()).getAccount().getUser();
	}
	
	private Account createPaypalletAccount() {
		CurrencyT c = new CurrencyT();
		c.setCurrency(Settings.getSetting("user.currency"));
		String currencyJson = JsonUtil.toJson(c);
		HttpEntity<?> request = RestTemplateUtil.getHttpEntityRequest(currencyJson);
		return restTemplate.postForObject(URL.CREATE_PAYPALLET, request, Account.class);
	}
	
	private Account depositDefaultAmount(String accountId) {
		Deposit deposit = new Deposit();
		deposit.setAccountId(accountId);
		MonetaryAmount monetaryAmount = new MonetaryAmount();
		Long depositAmount = Long.parseLong(Settings.getSetting("user.initial.deposit"));
		monetaryAmount.setAmount(depositAmount);
		monetaryAmount.setCurrency(Settings.getSetting("user.currency"));
		deposit.setMonetaryAmount(monetaryAmount);
		String depositJson = JsonUtil.toJson(deposit);
		LOGGER.info("DEPOSIT JSON "  +depositJson);
		HttpEntity<?> requestDeposit = RestTemplateUtil.getHttpEntityRequest(depositJson);
		return restTemplate.postForObject(URL.DEPOSIT_PAYPALLET, requestDeposit, Account.class);
	}

	@Override
	public List<Account> getAllAccounts() {
		LOGGER.info("UserService.getAllAccounts");
		ResponseEntity<Account[]> responseEntity  = restTemplate.getForEntity(URL.GETALL_PAYPALLET, Account[].class);
		Account[] accountArray = responseEntity.getBody();
		List<Account> accountList = Arrays.asList(accountArray);
		return accountList;
	}

}
