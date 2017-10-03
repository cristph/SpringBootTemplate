package com.cristph.template.core.service;

import java.util.List;

public interface BaseService<T> {

    //分页查询，从当前页currentPage开始,查询pageSize条数据
    List<T> search(int currentPage, int pageSize);

    List<T> getAll();

    List<T> search(int currentPage, int pageSize, String searchContent);

    T getEntityByKey(Object key);

    int add(T entity);

    int addWithDefaultValues(T entity);

    int delete(Object key, Long operatorId);

    int delete(List<Object> keys, Long operatorId);

    int batchDelete(Object[] keys, Long operatorId);

    //所有字段都会被更新
    int updateAll(T entity);

    //更新的时候按主键来更新，只用非空字段才会被更新
    int updateNotNull(T entity);
}
