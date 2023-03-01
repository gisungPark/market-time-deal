package com.market.timedeal.domain.user.api;

import com.market.timedeal.domain.user.domain.User;
import com.market.timedeal.domain.user.dto.LoginDto;
import com.market.timedeal.domain.user.dto.SignUpDto;
import com.market.timedeal.domain.user.service.UserService;
import com.market.timedeal.domain.user.dto.CustomResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CustomResponseEntity> login(@RequestBody LoginDto loginDto) {
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
