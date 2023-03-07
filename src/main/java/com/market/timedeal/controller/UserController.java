package com.market.timedeal.controller;

import com.market.timedeal.domain.User;
import com.market.timedeal.dto.request.LoginDto;
import com.market.timedeal.dto.request.SignUpDto;
import com.market.timedeal.service.UserService;
import com.market.timedeal.dto.CustomResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CustomResponseEntity> signUp(@RequestBody SignUpDto signUpDto) {

        userService.signUp(signUpDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomResponseEntity("회원가입에 성공했습니다."));
    }


    @PostMapping("/login")
    public ResponseEntity<CustomResponseEntity> login(@RequestBody LoginDto loginDto, HttpSession httpSession) {
        User user = userService.login(loginDto);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).
                    body(new CustomResponseEntity("아이디, 비밀번호를 확인하세요"));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomResponseEntity("로그인 성공", user));
    }

}
