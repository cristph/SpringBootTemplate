package com.cristph.template.core.service.user.impl;

import com.cristph.template.core.dao.mapper.UserMapper;
import com.cristph.template.core.pojos.entity.User;
import com.cristph.template.core.service.BaseService;
import com.cristph.template.core.service.BaseServiceImpl;
import com.cristph.template.core.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService{

}
