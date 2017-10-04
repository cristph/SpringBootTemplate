package com.cristph.template.core.service.user;

import com.cristph.template.core.pojos.entity.User;
import com.cristph.template.core.service.BaseService;

import java.util.List;

public interface UserService extends BaseService<User>{

    boolean isUserNameExist(String userName);

    boolean isUserIdExist(Long id);

    boolean isOtherUserNameDuplicate(Long id, String userName);

    List<User> fuzzySearchByName(String keyword, int currentPage, int pageSize);
}
