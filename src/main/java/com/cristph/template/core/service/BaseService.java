package com.cristph.template.core.service;

import java.util.List;

public interface BaseService<T> {

    List<T> listPage(int currentPage, int pageSize);

    List<T> listAll();

    T searchByKey(Object key);

    int add(T entity, Object operatorId);

    int addSelective(T entity, Object operatorId);

    int softDelete(Object key, Object operatorId);

    int batchSoftDelete(Object[] keys, Object operatorId);

    int hardDelete(Object key);

    int batchHardDelete(Object[] keys);

    int update(T entity, Object operatorId);

    int updateSelective(T entity, Object operatorId);
}
