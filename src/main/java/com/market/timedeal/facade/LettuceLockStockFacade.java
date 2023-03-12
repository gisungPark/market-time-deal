package com.market.timedeal.facade;

import com.market.timedeal.domain.Product;
import com.market.timedeal.repository.RedisLockRepository;
import com.market.timedeal.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

    private RedisLockRepository redisLockRepository;

    private ProductService productService;

    public LettuceLockStockFacade(RedisLockRepository redisLockRepository, ProductService productService) {
        this.redisLockRepository = redisLockRepository;
        this.productService = productService;
    }

    public void decrease(Product product, int quantity) throws InterruptedException {
        Long key = product.getId();
        while(!redisLockRepository.lock(key)) {
            Thread.sleep(100);
        }

        try {
            productService.decreaseProductQuantity(product, quantity);
        } finally {
            redisLockRepository.unlock(key);
        }

    }
}
