package com.example.api.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class FailCountRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public FailCountRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void incrementFail() {
        redisTemplate
                .opsForValue()
                .increment("fail_count");
    }
}
