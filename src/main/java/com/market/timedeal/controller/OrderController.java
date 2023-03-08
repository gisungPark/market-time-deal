package com.market.timedeal.controller;

import com.market.timedeal.domain.User;
import com.market.timedeal.dto.CustomResponseEntity;
import com.market.timedeal.dto.request.OrderRequest;
import com.market.timedeal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<CustomResponseEntity> createOrder(OrderRequest orderRequest){
        User customer = new User();

//        orderService.checkout(orderRequest, customer);
        return null;
    }
}
