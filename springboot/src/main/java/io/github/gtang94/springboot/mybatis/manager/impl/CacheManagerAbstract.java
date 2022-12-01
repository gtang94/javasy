package io.github.gtang94.springboot.mybatis.manager.impl;

import io.github.gtang94.springboot.mybatis.manager.CacheManagerInterface;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public abstract class CacheManagerAbstract<T> implements CacheManagerInterface {

    @Resource
    protected RedisTemplate redisTemplate;

}
