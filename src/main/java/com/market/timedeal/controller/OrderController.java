package com.market.timedeal.controller;

import com.market.timedeal.dto.CustomResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/checkout")
    public ResponseEntity<CustomResponseEntity> createOrder(){
        return null;
    }
}
