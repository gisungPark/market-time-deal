package com.market.timedeal.facade;

import com.market.timedeal.domain.Product;
import com.market.timedeal.repository.RedisLockRepository;
import com.market.timedeal.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedissonLockFacadeTest {

    @Autowired
    private RedissonLockFacade redisLockRepository;

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void after() {
        Product findProduct = productService.findByProductById(1L);
        findProduct.updateQuantity(100);
        productService.updateProduct(findProduct);
    }

    @Test
    @DisplayName("상품 재고 감소 - RedissonLockFacade")
    public void 상품재고감소_테스트05() throws InterruptedException {

        Product product = productService.findByProductById(1L);
        int quantity = product.getQuantity();

        System.out.println("수량 :" + quantity);
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(quantity);

        for (int i = 0; i < quantity; i++) {
            executorService.submit(() -> {
                try {
                    redisLockRepository.decrease(product, 1);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        assertEquals(0, productService.findByProductById(1L).getQuantity());
    }
}