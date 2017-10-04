package com.cristph.template.core.service;

import com.cristph.template.core.dao.BaseMapper;
import com.cristph.template.core.pojos.entity.BaseEntity;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

public class BaseServiceImpl<T extends BaseEntity, Mapper extends BaseMapper<T>> implements BaseService<T> {

    @Autowired
    public Mapper mapper;

    @Override
    public List<T> listPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return mapper.selectAll();
    }

    @Override
    public List<T> listAll() {
        return mapper.selectAll();
    }

    @Override
    public T searchByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int add(T entity, Object operatorId) {
        entity.setCreateOperator((Long) operatorId);
        entity.setUpdateOperator((Long) operatorId);
        Date now = new Date(System.currentTimeMillis());
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        return mapper.insert(entity);
    }

    @Override
    public int addSelective(T entity, Object operatorId) {
        entity.setCreateOperator((Long) operatorId);
        entity.setUpdateOperator((Long) operatorId);
        Date now = new Date(System.currentTimeMillis());
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        return mapper.insertSelective(entity);
    }

    @Override
    public int update(T entity, Object operatorId) {
        entity.setUpdateOperator((Long) operatorId);
        Date now = new Date(System.currentTimeMillis());
        entity.setUpdateTime(now);
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateSelective(T entity, Object operatorId) {
        entity.setUpdateOperator((Long) operatorId);
        Date now = new Date(System.currentTimeMillis());
        entity.setUpdateTime(now);
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int softDelete(Object key, Object operatorId) {
        Date now = new Date(System.currentTimeMillis());
        return mapper.softDeleteByPrimaryKey(key, now, operatorId);
    }

    @Override
    public int batchSoftDelete(Object[] keys, Object operatorId) {
        Date now = new Date(System.currentTimeMillis());
        return mapper.batchSoftDelete(keys, now, operatorId);
    }

    @Override
    public int hardDelete(Object key) {
        return mapper.hardDeleteByPrimaryKey(key);
    }

    @Override
    public int batchHardDelete(Object[] keys) {
        return mapper.batchHardDelete(keys);
    }

}
