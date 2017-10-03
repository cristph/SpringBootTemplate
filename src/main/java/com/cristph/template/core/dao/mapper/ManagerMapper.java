package com.cristph.template.core.dao.mapper;

import com.cristph.template.core.dao.BaseMapper;
import com.cristph.template.core.pojos.entity.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ManagerMapper extends BaseMapper<Manager> {

    long getOtherUserNameCount(@Param("userName") String userName, @Param("id") Long id);

    long getUserNameCount(String userName);

    long getUserCountById(Long id);

    long getUserCountByName(@Param("userName") String userName, @Param("password") String password);

    long updateAccess(@Param("idList") List<Object> ids);

    List<Manager> getManagerList(String userName);
}