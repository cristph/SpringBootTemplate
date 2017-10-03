package com.cristph.template.core.dao;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by ENLINK on 2017/8/14.
 */
public interface BaseMapper<T> {

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Object key);

    List<T> selectByCondition(T t);

    int updateByPrimaryKeySelective(T t);

    int updateAll(T t);

    int batchSoftDelete(@Param("list") List<Object> list,
                        @Param("deleteTime") Date deleteTime,
                        @Param("operatorId") Long operatorId);

    List<T> search(@Param("searchContent") String searchContent);

    List<T> selectAll();
}
