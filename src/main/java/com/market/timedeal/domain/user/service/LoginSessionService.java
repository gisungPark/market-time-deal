package com.market.timedeal.domain.user.service;

import com.market.timedeal.domain.user.domain.User;
import com.market.timedeal.domain.user.repository.UserRepository;
import com.market.timedeal.domain.user.util.PasswordEncrypter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginSessionService implements LoginService {

    private static final String USER_ID = "USER_INFO";

    private final UserRepository userRepository;

    private final HttpSession httpSession;

    @Override
    public User login(String userId, String password) {
        User findUser = userRepository.findByUserId(userId);

        if (findUser == null
                || !isMathPassword(password, findUser.getPassword())) {
            return null;
        }

        httpSession.setAttribute(USER_ID, findUser);
        return findUser;
    }

    @Override
    public void logout(String userId) {
        httpSession.removeAttribute(userId);
    }

    private boolean isMathPassword(String password, String hashedPassword) {
        return PasswordEncrypter.isMatch(password, hashedPassword);
    }

}
