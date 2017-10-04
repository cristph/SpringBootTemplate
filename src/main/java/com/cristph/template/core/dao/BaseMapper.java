package com.cristph.template.core.dao;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface BaseMapper<T> {

    //插入
    int insert(T t);

    int insertSelective(T t);

    //查询
    T selectByPrimaryKey(Object key);

    List<T> selectAll();

    //更新
    int updateByPrimaryKey(T t);

    int updateByPrimaryKeySelective(T t);

    //软删除
    int softDeleteByPrimaryKey(Object key,
                               @Param("deleteTime") Date deleteTime,
                               @Param("deleteOperatorId") Object deleteOperatorId);

    int batchSoftDelete(@Param("ids") Object[] ids,
                        @Param("deleteTime") Date deleteTime,
                        @Param("deleteOperatorId") Object deleteOperatorId);

    //硬删除
    int hardDeleteByPrimaryKey(Object key);

    int batchHardDelete(@Param("ids") Object[] ids);
}
