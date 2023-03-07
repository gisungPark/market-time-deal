package com.market.timedeal.service;

import com.market.timedeal.domain.Order;
import com.market.timedeal.domain.Product;
import com.market.timedeal.domain.User;
import com.market.timedeal.exception.NotFoundException;
import com.market.timedeal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;

    public void checkout(List<Order> orders, User customer) {

        for (Order order : orders) {
            Product product = order.getProduct();
            if (isOrder(product)) {
                //todo. 구매진행!
                orderRepository.save(order);
            }
        }
    }

    public boolean isOrder(Product product) {
        try {
            Product findProduct = productService.findByProductById(product.getId());
            if (findProduct.getQuantity() == 0) {
                log.info("{} 의 재고가 부족합니다.", product);
                return false;
            }
        } catch (NotFoundException exception) {
            log.error("{} 을 찾을 수 없습니다.", product);
            return false;
        }
        return true;
    }


}
