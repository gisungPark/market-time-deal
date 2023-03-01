package com.market.timedeal.service;

import com.market.timedeal.domain.User;
import com.market.timedeal.dto.request.LoginDto;
import com.market.timedeal.repository.UserRepository;
import com.market.timedeal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입_확인() {
//        SignUpDto newUser = SignUpDto.builder()
//                .userId("test-id")
//                .name("홍길동")
//                .address("서울특별시")
//                .password("1234")
//                .build();
//
//        userService.signUp(newUser);
        User findUser = userRepository.findByUserId("test-id");
        System.out.println(findUser.toString());

//        List<User> all = userRepository.findAll();
//        for(User u: all){
//            System.out.println(u.toString());
//        }
    }


    @Test
    public void 로그인_확인() {
        LoginDto user = LoginDto.builder()
                .userId("test-id")
                .password("1234")
                .build();
        User loginUser = userService.login(user);

        assertNotNull(loginUser);
    }


}