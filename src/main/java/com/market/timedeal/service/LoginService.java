package com.market.timedeal.service;

import com.market.timedeal.domain.User;

public interface LoginService {
    public User login(String userId, String password);

    public void logout(String userId);
}
