package com.market.timedeal.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@Component
public class RedisLockRepository {

    private RedisTemplate<String, String> redisTemplate;

    public RedisLockRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean lock(Long key) {
        redisTemplate.opsForValue()
                .setIfAbsent(generateKey(key), "lock", Duration.ofMillis(3_000));
        return true;
    }

    public boolean unlock(Long key) {
        return redisTemplate.delete(generateKey(key));
    }

    public String generateKey(Long key) {
        return key.toString();
    }

}
