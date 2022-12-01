package io.github.gtang94.springboot.mybatis.manager.impl;

import com.alibaba.fastjson.JSON;
import io.github.gtang94.springboot.mybatis.manager.EntityManagerInterface;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

@Component
public class EntityManagerImpl<T> extends CacheManagerAbstract<T> implements EntityManagerInterface<T> {

    private static String PREFIX = "entity:{id}";

    @Override
    public boolean hasEntity(Long entityId) {
        String key = getKey(entityId);

        return redisTemplate.hasKey(key);
    }

    @Override
    public T getEntity(Long entityId, Class<T> clz) {
        String key = getKey(entityId);

        Map value = redisTemplate.opsForHash().entries(key);

//        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
//        Class<T> clz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        T entity = JSON.parseObject(JSON.toJSONString(value), clz);
        return entity;
    }

    @Override
    public void setEntity(Long entityId, T entity) {
        String key = getKey(entityId);

        Map value = JSON.parseObject(JSON.toJSONString(entity), Map.class);
        redisTemplate.opsForHash().putAll(key, value);
    }

    private String getKey(Long entityId) {
        String key = PREFIX.replace("{id}", String.valueOf(entityId));
        return key;
    }
}
