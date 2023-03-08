package com.market.timedeal.service;

import com.market.timedeal.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("상품 재고 감소 - 동시성 이슈")
    public void 상품재고감소_테스트() {

        Product product = productService.findByProductById(3L);

        System.out.println("재고 : " + product.getName() + " , " + product.getQuantity());
        int quantity = product.getQuantity();
        for (int i = 0; i < quantity; i++) {
            productService.decreaseProductQuantity(product, 1);
        }

        assertEquals(0, productService.findByProductById(1L).getQuantity());
    }

}