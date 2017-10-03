package com.cristph.template.core.service.usermanager;


import com.cristph.template.core.pojos.entity.Manager;
import com.cristph.template.core.service.CommonService;

/**
 * Created by Administrator on 2017/8/10.
 */
public interface ManagerService extends CommonService<Manager> {
    boolean isOtherUserNameExist(String userName, Long id);

    boolean isUserNameExist(String userName);

    boolean isUserExist(Long id);

    boolean isUserExist(String userName, String password);

    boolean isSuperAdministrator(Long id);

    long updateAccess(Object[] ids);

    Manager getManager(String userName);
}
