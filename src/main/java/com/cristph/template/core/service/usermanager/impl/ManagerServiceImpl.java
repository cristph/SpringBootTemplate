package com.cristph.template.core.service.usermanager.impl;

import com.cristph.template.constraint.Constraints;
import com.cristph.template.core.dao.mapper.ManagerMapper;
import com.cristph.template.core.pojos.entity.Manager;
import com.cristph.template.core.service.CommonServiceImpl;
import com.cristph.template.core.service.usermanager.ManagerService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

@Service
public class ManagerServiceImpl extends CommonServiceImpl<Manager, ManagerMapper> implements ManagerService {

    @Override
    public boolean isOtherUserNameExist(String userName, Long id) {
        long count = mapper.getOtherUserNameCount(userName, id);
        return count != 0;
    }

    @Override
    public boolean isUserNameExist(String userName) {
        long count = mapper.getUserNameCount(userName);
        return count != 0;
    }

    @Override
    public boolean isUserExist(Long id) {
        long count = mapper.getUserCountById(id);
        return count != 0;
    }

    @Override
    public boolean isUserExist(String userName, String password) {
        long count = mapper.getUserCountByName(userName, password);
        return count > 0;
    }

    @Override
    public boolean isSuperAdministrator(Long id) {
        Manager manager = mapper.selectByPrimaryKey(id);
        if (manager.getRoleName().trim().equals(Constraints.SUPER_ADMINISTRATOR)) {
            return true;
        }
        return false;
    }

    @Override
    public long updateAccess(Object[] ids) {
        List<Object> idList = Arrays.asList(ids);
        return mapper.updateAccess(idList);
    }

    @Override
    public Manager getManager(String userName) {
        List<Manager> list = mapper.getManagerList(userName);
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }
}
