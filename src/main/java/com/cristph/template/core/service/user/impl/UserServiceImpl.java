package com.cristph.template.core.service.user.impl;

import com.cristph.template.core.dao.mapper.UserMapper;
import com.cristph.template.core.pojos.entity.User;
import com.cristph.template.core.service.BaseServiceImpl;
import com.cristph.template.core.service.user.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {

    @Override
    public boolean isUserNameExist(String userName) {
        Long count = mapper.countByName(userName);
        return count > 0;
    }

    @Override
    public boolean isUserIdExist(Long id) {
        Long count = mapper.countById(id);
        return count > 0;
    }

    @Override
    public boolean isOtherUserNameDuplicate(Long id, String userName) {
        Long count = mapper.countByOtherUserName(id, userName);
        return count > 0;
    }

    @Override
    public List<User> fuzzySearchByName(String keyword, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return mapper.fuzzySearchByName(keyword);
    }
}
