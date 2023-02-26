package com.market.timedeal.domain.user.service;

import com.market.timedeal.domain.user.domain.Role;
import com.market.timedeal.domain.user.domain.User;
import com.market.timedeal.domain.user.dto.LoginDto;
import com.market.timedeal.domain.user.dto.SignUpDto;
import com.market.timedeal.domain.user.repository.UserRepository;
import com.market.timedeal.domain.user.util.PasswordEncrypter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(SignUpDto signUpDto) {

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

    public User login(LoginDto user) {
        User findUser = userRepository.findByUserId(user.getUserId());

        if (findUser == null) return null;
        if (comparePassword(user.getPassword(), findUser.getPassword())) {
            return findUser;
        }
        return null;
    }

    private boolean comparePassword(String password, String hashedPassword) {
        return PasswordEncrypter.isMatch(password, hashedPassword);
    }


}
