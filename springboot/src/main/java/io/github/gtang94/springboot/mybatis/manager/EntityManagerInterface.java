package io.github.gtang94.springboot.mybatis.manager;

public interface EntityManagerInterface<T> {

    boolean hasEntity(Long entityId);

    T getEntity(Long entityId, Class<T> clz);

    void setEntity(Long entityId, T entity);
}
