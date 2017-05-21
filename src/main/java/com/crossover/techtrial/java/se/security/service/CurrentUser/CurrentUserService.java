package com.crossover.techtrial.java.se.security.service.CurrentUser;

import com.crossover.techtrial.java.se.security.domain.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}