package com.market.timedeal.service;

import com.market.timedeal.domain.Order;
import com.market.timedeal.domain.Product;
import com.market.timedeal.domain.User;
import com.market.timedeal.dto.request.OrderRequest;
import com.market.timedeal.exception.NotFoundException;
import com.market.timedeal.repository.OrderRepository;
import com.market.timedeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;

    private UserRepository userRepository;

    @Transactional
    public boolean checkout(OrderRequest order) {

        Product product = order.getProduct();

        if (!purchaseProduct(order)) {
            log.info("{} 구매 실패", product.getName());
            return false;
        }
        log.info("{} 구매 성공", product.getName());

        int totalPrice = product.getPrice() * order.getQuantity();

        User customer = userRepository.findById(1L).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Order newOrder = order.createOrderEntity(customer, totalPrice);

        orderRepository.saveAndFlush(newOrder);
        return true;
    }

    public boolean purchaseProduct(OrderRequest order) {
        return productService.decreaseProductQuantity(order.getProduct(), order.getQuantity());
    }


}
