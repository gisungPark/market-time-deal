package com.market.timedeal.service;

import com.market.timedeal.domain.Role;
import com.market.timedeal.domain.User;
import com.market.timedeal.dto.request.LoginDto;
import com.market.timedeal.dto.request.SignUpDto;
import com.market.timedeal.exception.DuplicatedIdException;
import com.market.timedeal.repository.UserRepository;
import com.market.timedeal.util.PasswordEncrypter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private UserRepository userRepository;

    private LoginService loginService;

    public UserService(UserRepository userRepository, LoginService loginService) {
        this.userRepository = userRepository;
        this.loginService = loginService;
    }

    public void signUp(SignUpDto signUpDto) throws DuplicatedIdException {

        if (isExistsId(signUpDto.getUserId())) {
            throw new DuplicatedIdException(signUpDto.getUserId() + " 는 이미 존재하는 ID 입니다.");
        }

        User newUser = User.builder()
                .userId(signUpDto.getUserId())
                .name(signUpDto.getName())
                .password(PasswordEncrypter.encrytp(signUpDto.getPassword()))
                .address(signUpDto.getAddress())
                .role(signUpDto.getRole() == null ? Role.ROLE_USER : signUpDto.getRole())
                .createTime(LocalDateTime.now())
                .build();
        userRepository.saveAndFlush(newUser);
    }

    private boolean isExistsId(String userId) {
        User byUserId = userRepository.findByUserId(userId);
        return byUserId != null;
    }

    public User login(LoginDto user) {

        User findUser = loginService.login(user.getUserId(), user.getPassword());
        if (findUser == null) return null;

        return findUser;
    }

}
