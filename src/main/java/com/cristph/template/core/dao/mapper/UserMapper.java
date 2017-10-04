package com.cristph.template.core.dao.mapper;


import java.util.List;

import com.cristph.template.core.dao.BaseMapper;
import com.cristph.template.core.pojos.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User>{

    Long countByName(String keyword);

    Long countById(Long id);

    Long countByOtherUserName(@Param("id") Long id, @Param("keyword") String keyword);

    List<User> fuzzySearchByName(@Param("keyword") String keyword);
}