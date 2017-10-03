package com.cristph.template.core.service;

import com.cristph.template.core.dao.BaseMapper;
import com.cristph.template.core.pojos.entity.BaseEntity;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CommonServiceImpl<T extends BaseEntity, Mapper extends BaseMapper<T>> implements CommonService<T> {

    @Autowired
    public Mapper mapper;

    @Override
    public List<T> search(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return mapper.selectAll();
    }

    @Override
    public List<T> getAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> search(int currentPage, int pageSize, String searchContent) {
        PageHelper.startPage(currentPage, pageSize);
        return mapper.search(searchContent);
    }

    @Override
    public T getEntityByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int add(T entity) {
        entity.setCreateTime(new Date(System.currentTimeMillis()));
        entity.setUpdateTime(new Date(System.currentTimeMillis()));
        return mapper.insertSelective(entity);
    }

    @Override
    public int addWithDefaultValues(T entity) {
        return mapper.insertSelective(entity);
    }

    @Override
    public int delete(Object key, Long operatorId) {
        T t = mapper.selectByPrimaryKey(key);
        if (t != null) {
            t.setDeleteFlag(1L);
            t.setDeleteOperator(operatorId);
            t.setDeleteTime(new Date(System.currentTimeMillis()));
            return mapper.updateByPrimaryKeySelective(t);
        }
        return 0;
    }

    @Override
    public int delete(List<Object> keys, Long operatorId) {
        //添加-1L纯属是为了mybatis在动态使用foreach的时候list非空不生效的问题
        keys.add(-1L);
        return mapper.batchSoftDelete(keys, new Date(System.currentTimeMillis()), operatorId);
    }

    @Override
    public int batchDelete(Object[] keys, Long operatorId) {
        List<Object> list = new ArrayList();
        for (Object key : keys) {
            list.add(key);
        }
        return delete(list, operatorId);
    }

    @Override
    public int updateAll(T entity) {
        entity.setUpdateTime(new Date(System.currentTimeMillis()));
        return mapper.updateAll(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        entity.setUpdateTime(new Date(System.currentTimeMillis()));
        return mapper.updateByPrimaryKeySelective(entity);
    }
}
