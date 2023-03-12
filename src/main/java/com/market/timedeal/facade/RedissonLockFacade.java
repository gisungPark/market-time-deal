package com.market.timedeal.facade;

import com.market.timedeal.domain.Product;
import com.market.timedeal.service.ProductService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockFacade {

    private RedissonClient redissonClient;

    private ProductService productService;

    public RedissonLockFacade(RedissonClient redissonClient, ProductService productService) {
        this.redissonClient = redissonClient;
        this.productService = productService;
    }

    public void decrease(Product product, int quantity) {
        RLock lock = redissonClient.getLock(product.getId().toString());

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!available) {
                System.out.println("lock 획득 실패");
                return;
            }

            productService.decreaseProductQuantity(product, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
