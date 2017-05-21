package com.crossover.techtrial.java.se.security.service.CurrentUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.java.se.security.domain.CurrentUser;
import com.crossover.techtrial.java.se.security.domain.Role;


@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserServiceImpl.class);

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.Admin || currentUser.getId().equals(userId));
    }

}