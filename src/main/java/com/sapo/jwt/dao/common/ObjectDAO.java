package com.sapo.jwt.dao.common;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ObjectDAO<T> {
    int create(T obj);
    int update(T obj, long id, boolean status);
    List<T> findAll(String sql, Class<T> zClass);
    T findOne(String sql, Class<T> zClass);
    Long getLong(String sql);
    void delete(String sql);
}
