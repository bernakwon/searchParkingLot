package com.berna.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        CaffeineCache caches = new CaffeineCache("API_ALL_DATA",  Caffeine.newBuilder().recordStats()
                                .expireAfterWrite(5*60, TimeUnit.SECONDS)
                                .maximumSize(10000)
                                .build());

        cacheManager.setCaches(Collections.singleton(caches));
        return cacheManager;
    }
}