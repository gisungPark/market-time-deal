package com.market.timedeal.domain.user.api;

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
    public ResponseEntity<CustomResponseEntity> signUp(@RequestBody SignUpDto signUpDto){

        userService.signUp(signUpDto);
        return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new CustomResponseEntity("회원가입에 성공했습니다."));
    }


}
