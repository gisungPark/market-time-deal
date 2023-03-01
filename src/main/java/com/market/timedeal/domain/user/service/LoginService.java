package com.market.timedeal.domain.user.service;

import com.market.timedeal.domain.user.domain.User;

public interface LoginService {
    public User login(String userId, String password);

    public void logout(String userId);
}
